package game.MainControl;



import java.awt.Shape;
import java.util.ArrayList;
import java.util.Comparator;
import game.Object.Enemy;
import game.Object.GameObject;
import game.Object.Hunter;
import game.Object.Gun.bullet;
import game.Object.Item;
import game.Object.MainChar;
import game.Object.Weapon;

public class Map { // nhiem vu: lam trung gian tac dong giua cac ben
	private final int BIT_LEN = 50;//TODO: gop vs render 
	public Shape shape;
	private int[][] map;
	private ArrayList<GameObject> enemy;
	private ArrayList<GameObject> ListItem;
	private GameObject mainC;
	private int ID;
//	private GameObject bullet;
	private ArrayList<GameObject> Other;


	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int[][] getMap() {
		return map;
	}
	public void add(bullet bullet1){
		//this.bullet=bullet1;
		Other.add(bullet1);
		bullet1.setMap(this);
		
	}
	
	public ArrayList<GameObject> getBullet() {
		return Other;
	}

//	public void setBulltet(GameObject bullet) {
//		this.bullet = bullet;
//		
//	}

	public void setMap(int[][] map) {
		this.map = map;

	}

public void add(Hunter hunter){
	Other.add(hunter);
}
	public void remove(Enemy en) {
		enemy.remove((GameObject)en);
		
	}

	public void remove(Item en) {
		ListItem.remove((GameObject)en);
		
	}
	public void remove (MainChar main){
		mainC=null;
	}
	public void add(MainChar main){
		this.mainC=main;
		
		main.setMap(this);
	}
	public void add(Item item){
		this.ListItem.add((GameObject)item);
		
		item.setMap(this);
	}
	public void add(Enemy enemy){
		
		this.enemy.add((GameObject)enemy);
		enemy.setMap(this);
	}
	public void remove(bullet b1){
	//	bullet=null;
		Other.remove(b1);
	}
	public Shape getShape() {
		return shape;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public ArrayList<GameObject> getEnemy() {
		return enemy;
	}

	public ArrayList<GameObject> getListItem() {
		return ListItem;
	}

	public MainChar getMainC() {
		return (MainChar)mainC;
	}

	public Map() {
		enemy = new ArrayList<GameObject>();
		ListItem = new ArrayList<GameObject>();
		Other=new ArrayList<GameObject>();
		ID=0;
	}

	public int Where(int y, int x) {
		if(y>=1600)
			y=1550;
		if(y<0)
			y=0;
		if(x>=900)
			x=850;
		if(x<0)
			x=0;
		return map[x / BIT_LEN][y / BIT_LEN];
	}

	public boolean GoingToNextDoor() {// TODO: xac dinh xem main da den cua chua
		if (Where(mainC.getX()+50,mainC.getY()+50) == 4) {
			
			if (((MainChar)mainC).isHaveKey())
				return true;
			return false;
		}
		return false;
	}

	public ArrayList<Enemy> kill(Weapon attacker) {
		ArrayList<Enemy> enemies = new ArrayList<>();
		enemy.sort(new Comparator<GameObject>() {

			@Override
			public int compare(GameObject o1, GameObject o2) {
				int x0=mainC.getX();
				int y0= mainC.getY();
				double x = Math.sqrt((Math.pow((double) (o1.getX() - x0), 2.0)
						+ Math.pow((double) (o1.getY() - y0), 2.0)));
				double y = Math.sqrt((Math.pow((double) (o2.getX() -x0), 2.0)
						+ Math.pow((double) (o2.getY() - y0), 2.0)));
				if (x < y)
					return -1;
				else if (x > y)
					return 1;
				else
					return 0;
			}
		});
		for (int i = 0; i < enemy.size(); i++) {
			if (attacker.Filter(enemy.get(i).getX(), enemy.get(i).getY()) == true) {
				enemies.add((Enemy)enemy.get(i));
				if (attacker.getNum() ==Weapon.One_One) {
					break;
				}
			}
		}
		return enemies;
	}

	public Item OneItemHere() {
		boolean haveOne;
		haveOne = false;
		int i;
		for (i = 0; i < ListItem.size(); i++) {
			
			if (mainC.getX()> ListItem.get(i).getX()-BIT_LEN/2&&mainC.getX()< ListItem.get(i).getX()+BIT_LEN/2&& mainC.getY()>ListItem.get(i).getY()-BIT_LEN/2&& mainC.getY()< ListItem.get(i).getY()+BIT_LEN/2) {
				haveOne = true;
				break;
			}
		}
		if (haveOne == true)
			return (Item)ListItem.get(i);
		else
			return null;
	}
	public void update(){
		mainC.update();
		for(int i=0;i<enemy.size();i++)
			enemy.get(i).update();
		for(int i=0;i<ListItem.size();i++){
			ListItem.get(i).update();
		}
		if(Other!=null){
			
	     for(int j=0;j<Other.size();j++)
		   ((GameObject)Other.get(j)).update();
		}
	}

}
