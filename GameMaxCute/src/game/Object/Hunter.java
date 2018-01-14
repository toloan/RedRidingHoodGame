package game.Object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.MainControl.Map;

public class Hunter extends Gun {
	private int huong;
	private boolean attacking;

	public Hunter(int x, int y, int time, Map map) {
		super(x, y, time, map);
		time=-1;
		this.Num=One_One;
		this.weaponType=GUN;
		attacking=false;
		map.getMainC().setKey();
	}

	@Override
	public void Attack(Enemy enemy) {
		//enemy.setHP(0);
		bulletHunter B = new bulletHunter(enemy,getX(),getY());
		map.add(B);
		System.out.println("bullet");
		//attacking=false;

	}

	@Override
	public boolean Filter(int x, int y) {
		double distance = Math.sqrt(Math.pow((x - this.getX()), 2) + Math.pow((y - this.getY()), 2));

		if (this.huong ==PHAI && distance < 200 && x <= this.getX()&&(
																			 y<(this.getY()+50)&&y>(this.getY()-50)
																			 ))
			return true;
		if (this.huong== TRAI && distance < 200 && x >= this.getX() &&y<(this.getY()+50)&&(y>this.getY()-50
																			))
			return true;
		if (this.huong == DUOI && distance < 200 && y <= this.getY()&&x<(this.getX()+50)&&(x>this.getX()-50)
																			 )
			return true;
		if (this.huong== TREN && distance < 200 && y >= this.getY()&&x<(this.getX()+50)&&x>(this.getX()-50)
																			 )
			return true;
		return false;
	}
	@Override
	public void setInUse(MainChar main) {
//		System.out.println(main.getHuong()+" "+main.getX()+" "+ main.getY());
		this.huong=main.getHuong();
		int x0=main.getX();
		int y0=main.getY();
		if(x0>1500)
			x=x0-50;
		else
			x=x0+50;
		if(y0>800)
			y=y0-50;
		else 
			y=y0+50;
		if(attacking==false){
			attacking=true;
			
		ArrayList<Enemy> En=getMap().kill(this);
		System.out.println("load from map");
		if(En!=null){
			System.out.println("ban dc");
			System.out.println(En.size());
			for(int i=0;i<En.size();i++){		

				Attack(En.get(i));
			}
		}
		}
	}

	@Override
	public void draw(Graphics g) {
		BufferedImage img = null;
		switch (huong) {
			case TRAI:
				try {
					img = ImageIO.read(new File("src/sprite/hunter_left.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, 50, 50, null);
				break;
			case DUOI:
				try {
					img = ImageIO.read(new File("src/sprite/hunter_front.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, 50, 50, null);
				break;
			case TREN:
				try {
					img = ImageIO.read(new File("src/sprite/hunter_back.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, 50, 50, null);
				break;
			case PHAI:
				try {
					img = ImageIO.read(new File("src/sprite/hunter_right.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, 50, 50, null);
				break;
		}

	}

	@Override
	public void update() {
		setInUse(map.getMainC());
	}
public class bulletHunter extends Gun.bullet{

	public bulletHunter(Enemy enemy, int x, int y) {
		super(enemy, x, y);
	}
	@Override
	public void update() {
		this.x = this.x - 8 * (this.x - x0) / 25;
        this.y = this.y - 8 * (this.y - y0) / 25;

        if (this.x > x0 - 10 && this.x <= x0 + 25 && this.y > y0 - 10 && this.y <= y0 + 25) {
            ArrayList<Enemy> enemies;
            enemies = getMap().kill(this);
            if (enemies != null){
                for (int i = 0; i < enemies.size(); i++)
                    Attack(enemies.get(i));
                attacking=false;
            }
            getMap().remove(this);
        } 
    }
	
	
}
}
