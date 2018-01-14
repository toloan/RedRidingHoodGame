package game.MainControl;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import game.Object.Boom;
import game.Object.Coin;
import game.Object.Enemy;
import game.Object.Gun;
import game.Object.HP;
import game.Object.Hunter;
import game.Object.Item;
import game.Object.MainChar;
import game.Object.Mana;
import game.Object.Shoes;
import game.Userface.SentResult;

public class MapOne extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map map;
	public Thread thread;
	private SentResult sent;
	public boolean isRunning;
	private int level;
	private Input input;
	private Render render;
	public MapOne(SentResult sent, int level, MainChar mainC) {
		super();
	//	level=3;
		isRunning = true;
		this.sent = sent;
		this.level=level;
		
		try {
			input=new Input(level);
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		createMap(mainC);
		render = new Render(level, map);// TODO: moi
	   addKeyListener(new KeyAdapter() { 
	    
			@Override
			public void keyPressed(KeyEvent e) {
				if(map.getMainC().isActive()==true){
				if (e.isControlDown() && e.getKeyChar() != 's' && e.getKeyCode() == 83) {
					sent.sentResult("save");
				} else {
					map.getMainC().move(e.getKeyChar()); // cho nay dung ham cua Tuyen
					repaint();
				}
			}
			}
	    });
		setFocusable(true);

	}

	public void createMap(MainChar mainC) {
		map = new Map();
		map.setID(1000);
		ArrayList<Item> ListItem= new ArrayList<Item>();
		ArrayList<Enemy> enemies= new ArrayList<Enemy>();
	try {
		map.setMap(input.InputMap());
		ListItem=input.InputItem();
		enemies= input.InputEnemy();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	for(int i=0;i<ListItem.size();i++)
		map.add(ListItem.get(i));
	for(int i=0;i<enemies.size();i++)
		map.add(enemies.get(i));
		map.add(mainC.clone()); //TODO: thay setMain= addMain
		

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		render.draw(g);

	}

	public void startGame() {

		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}

	}
	public void makeNewItem(int x, int y){
		Random random=new Random();
		if(map.getMainC().isHaveKey()==false&& map.getEnemy().size()>0){
		int i= random.nextInt(6);
		switch(i){
			case 1:
				map.add(new HP(x, y, 1));
				repaint();
				break;
			case 2:
				map.add(new Coin(x, y, 2));
				break;
			case 3: 
				map.add(new Shoes(x, y, 2));
				break;
			
			case 0: 
				map.add(new Hunter(x, y, -1, map));
				break;
			case 4: 
				map.add(new Mana(x,y,1));
				break;
			case 5: 
				map.add(new Gun(x,y,4,map));
				break;
			case 6:
				map.add(new Boom(x, y, 2, map));
			
		}
		}
		else if(map.getMainC().isHaveKey()==false&& map.getEnemy().size()==0){
			map.add(new Hunter(x, y, -1, map));
		}
		else if(map.getMainC().isHaveKey()==true){
			int i= random.nextInt(6);
			switch(i){
				case 1:
					map.add(new HP(x, y, 1));
					repaint();
					break;
				case 2:
					map.add(new Coin(x, y, 2));
					break;
				case 0: 
					map.add(new Shoes(x, y, 2));
					break;
				case 4: 
					map.add(new Mana(x,y,1));
					break;
				case 5: 
					map.add(new Gun(x,y,4,map));
					break;
				case 3:
					map.add(new Boom(x, y, 2, map));
			}
		}
	}

	public boolean eventChecking() {
		if (map.GoingToNextDoor()) {
			if (level <4)
				sent.sentResult("next");
			else
				sent.sentResult("final");
			return false;
		}
		if (map.getMainC().isDie()) {
			map.remove(map.getMainC());
			sent.sentResult("die");
			return false;
		}
		if (map.getMainC().getCoin() == 10) {
			map.getMainC().setCoin(map.getMainC().getCoin()+1);
			sent.sentResult("reward1");// TODO: reward1
			map.getMainC().setMaxHP(7);// TODO: sua
			return true;
		}
		if (map.getMainC().getCoin() == 20) { // TODO: reward2
			map.getMainC().setCoin(map.getMainC().getCoin()+1);
			sent.sentResult("reward2");
			map.getMainC().setSpeed(4); // TODO: sua
			return true;
		}
		if (map.getMainC().getCoin() == 30) { // TODO: reward3
			map.getMainC().setCoin(map.getMainC().getCoin()+1);
			sent.sentResult("reward3");
			return true;
		}
		for(int i=0;i<map.getEnemy().size();i++){
			int x; int y;
			if (((Enemy)map.getEnemy().get(i)).isDie()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x=map.getEnemy().get(i).getX();
                y=map.getEnemy().get(i).getY();
                map.remove((Enemy)(map.getEnemy().get(i)));
                makeNewItem(x, y);
			}
		}
		return true;
	}

	@Override

	public void run() {
		boolean change = true;
		long end = 0;
		while (isRunning) {
			try {
				isRunning=eventChecking();
				if(isRunning==false)
					break;
				Thread.sleep(20);
			
				if (map.shape != null && change == true) {
					end = System.currentTimeMillis() + 500;
					change = false;
				}
				if (map.shape != null && System.currentTimeMillis() >= end) {
					map.shape = null;
					change = true;
					
				}
     			map.update();
				repaint();

			} catch (InterruptedException e) {
			}
		}
		thread = null;
	}

	public MainChar getMain() {
		return map.getMainC().clone();
	}


}
