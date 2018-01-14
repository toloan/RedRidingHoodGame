package game.Object;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Ghost extends Enemy{

	public Ghost(int x, int y, int blood) {
		super(x, y, blood);
	}
	@Override
	public void attack() {
		MainChar main=map.getMainC();
		System.out.println("Mana: "+main.getMana());
		if(main.getMana()>0){
			System.out.println("tru");
			main.minusmana(power);;		
		}
		else if(map.getMainC().getMana()==0){
			map.getMainC().setActive(false);
			map.getMainC().setStop( System.currentTimeMillis());
			System.out.println("Stop main");
		}
		int a=main.getX();
		int b=main.getY();
		Random random=new Random();
		huong = random.nextInt(4)+1;// TODO: co quen viet chuyen dong r thi viet lai dum, trong chuyen dong no ngu hoc ma t luoi qua :|
		while(Math.sqrt(Math.pow((x-a),2)+Math.pow(y-b, 2))<=100){
			switch(huong){
			case TRAI:
				if(map.Where(x-speed, y)==1||map.Where(x-speed, y)==6)
					x=x-speed;
				else huong=PHAI;
				break;
			case PHAI:
				if(map.Where(x+speed, y)==1||map.Where(x+speed, y)==6)
					x=x+speed;
				else huong=TRAI;
				break;
			case TREN:
				if(map.Where(x, y-speed)==1||map.Where(x, y-speed)==6)
					y=y-speed;
				else huong=DUOI;
				break;
			case DUOI:
				if(map.Where(x, y+speed)==1||map.Where(x, y+speed)==6)
					y=y+speed;
				else huong=TREN;
				break;
			}
		}
		
	}
	@Override
	public void draw(Graphics g) {
		 g.setColor(Color.RED);
    	 for (int i = 0; i < this.HP; i++) {
            
             g.fillRect(x + i * 6, y-10, 20, 4);
         }
    	 g.setColor(Color.gray);
    	 int remain=this.maxHP-this.HP;
    	 for (int i = 0; i < remain; i++) {
             
             g.fillRect(x + (getHP() + i) * 6, y-10, 20, 4);
         }
		BufferedImage img = null;
				try {
					img = ImageIO.read(new File("src/sprite/ghost.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, 50, 50, null);
	}
	@Override
	public void update() {
		super.update();
	}
}