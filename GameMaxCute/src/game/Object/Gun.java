package game.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.MainControl.Map;

public class Gun extends Weapon {

    public Gun(int x, int y, int time, Map map) {
        super(x, y, time, map);
        this.Num = One_One;
        this.weaponType = GUN;

    }
//
//    public final int PHAI = 2;
//    public final int TRAI = 4;
//    public final int TREN = 1;
//    public final int DUOI = 3;
    private int huong;

    @Override
    public void setInUse(MainChar main) {
        this.huong = main.getHuong();
        x = main.getX();
        y = main.getY();
       if(this.time>0){
            time--;
            ArrayList<Enemy> En = getMap().kill(this);
            if (En != null)
                for (int i = 0; i < En.size(); i++)
                    Attack(En.get(i));
       }
    }

    @Override
    public void Attack(Enemy enemy) {// xac dinh tac dung cua vu khi len quai 
		bullet B = new bullet(enemy,getX(),getY());
		map.add(B);
    	// enemy.minushp(3);
    }

    @Override
    public boolean Filter(int x, int y) {// xac dnh xem co chon con enemy nay de
        // giet k //TODO: viet lai bo dinh
        // vi ke thu
        // //
    	int x1= this.getMap().getMainC().getX();
    	int y1=this.getMap().getMainC().getY();
        double distance = Math.sqrt(Math.pow((x - this.map.getMainC().getX()), 2) + Math.pow((y - this.getMap().getMainC().getY()), 2));

        if (this.huong == this.TRAI && distance < 1000 && x <= this.getX()
                && (y < (this.getMap().getMainC().getY() + 100) && y > (this.getMap().getMainC().getY() - 100)))
            return true;
        if (this.huong == this.PHAI && distance < 1000 && x >= this.getMap().getMainC().getX() && y < (this.getMap().getMainC().getY() + 100)
                && (y > this.getMap().getMainC().getY() - 100))
            return true;
        if (this.huong == this.TREN && distance < 500 && y <= this.getMap().getMainC().getY() && x < (this.getX() + 100)
                && (x > x1 - 100))
            return true;
        if (this.huong == this.DUOI && distance < 500 && y >= y1 && x < (x1 + 100)
                && x > (x1 - 100))
            return true;
        return false;
    }

    @Override
    public void draw(Graphics g) {
        // draw
        BufferedImage image;
        image = null;
        try {
            image = ImageIO.read(new File("src/sprite/gun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, this.getX(), this.getY(), 25, 25, null);
    }

    

    @Override
    public void update() {
        super.update();
    }
    public class bullet extends Weapon{
    	 protected int x0;
    	    protected int y0;
    	    // private Enemy enemy;

    	    public bullet(Enemy enemy,int x, int y) {
    	        super(x,y, 1, null);
    	     //   super(x,y, 1,null);
    	        this.x=x;
    	        this.y=y;
    	        x0 = enemy.getX();
    	        y0 = enemy.getY();
    	       
    	       
    	    }

    	    public bullet(int x, int y, int time, Map map) {
    	        super(x, y, time, map);
    	        this.Num = One_One;
    	        this.weaponType = GUN;
    	    }

    	    public void draw(Graphics g) {
    	        g.setColor(Color.BLACK);
    	        g.fillOval(this.x, this.y, 20, 20);
    	     //   g.fillRect(this.x, this.y, 25, 25);
    	       

    	    }

    	    @Override
    	    public void Attack(Enemy enemy) {

    	        enemy.minushp(1);
    	      


    	    }

    	    @Override
    	    public boolean Filter(int x, int y) {
    	        if (this.x > x - 10 && this.x <= (x + 25) && this.y > y - 10
    	                && this.y <= (y + 25))
    	            return true;
    	        return false;
    	    }

    	    @Override
    	    public void update() {
    	    	
    	    	this.x = this.x - 8 * (this.x - x0) / 25;
    	        this.y = this.y - 8 * (this.y - y0) / 25;

    	        if (this.x > x0 - 10 && this.x <= x0 + 25 && this.y > y0 - 10 && this.y <= y0 + 25) {
    	            ArrayList<Enemy> enemies;
    	            enemies = getMap().kill(this);
    	            if (enemies != null)
    	                for (int i = 0; i < enemies.size(); i++)
    	                    Attack(enemies.get(i));
    	            getMap().remove(this);
    	        } 
    	    }
    }

}
