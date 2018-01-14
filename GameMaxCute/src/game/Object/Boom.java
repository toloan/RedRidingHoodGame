package game.Object;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import game.MainControl.Map;

import javax.imageio.ImageIO;

public class Boom extends Weapon{
public Boom(int x, int y, int time, Map map) {
		super(x, y, time, map);
		this.Num=MoreThanOne;
		this.weaponType=BOOM;
	}

private final int PhamVi=200;
	@Override
	public void Attack(Enemy enemy) {
		enemy.setHP(0);
	}

	@Override
	public boolean Filter(int x, int y) {
		if(Math.sqrt(Math.pow((x-this.getX()), 2)-Math.pow((y-this.getY()),2))<PhamVi){
			return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/sprite/bomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, this.getX(), this.getY(), 25,25, null);


	}
	public int getPhamVi(){
		return PhamVi;
	}
	@Override
		public void setInUse(MainChar main) {
			super.setInUse(main);
		getMap().setShape((Shape) new Ellipse2D.Float((float)(main.getX()+25), (float)(main.getY()+25),(float) (PhamVi * 2),(float)(PhamVi * 2)));
		}

	@Override
	public void update() {
		
	}
}
