/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Object;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author QuangDai
 */
public class Boss extends Enemy{
private long time;
private Random random;
    public Boss(int x, int y, int blood) {
        super(x, y, blood);
        this.setPower(5);
        this.setWidth(50);
        time=System.currentTimeMillis();
        random=new Random();
        STEP = 100;
    }

    public Boss(int x, int y) {
        super(x, y);
        this.setPower(5);
        this.setWidth(50);
        this.setHP(2000);
    }
@Override
	public void attack() {
		super.attack();
	
    		
	}
public void Bear(){
	if((System.currentTimeMillis()-time)>30000){
//		int a1=x-300;
//		if(a1<0)
//			a1=0;
//		int b1=y-300;
//		if(b1<0)
//			b1=0;
//		int a2=x+300;
//		if(a2>MAP_RIGHT)
//			a2=MAP_RIGHT;
//		int b2=y+300;
//		if(b2>MAP_DOWN)
//			b2=MAP_DOWN;
		int x0,y0;
		for (int i=0;i<3;i++){
			do{
			
			 x0 = random.nextInt(MAP_RIGHT)+MAP_LEFT;
			 y0 = random.nextInt(MAP_DOWN)+MAP_UP;
			 }while(map.Where(x0, y0)!=1||map.Where(x0+50, y0)!=1||map.Where(x0, y0+50)!=1||map.Where(x0+50, y0+50)!=1);
			int j=random.nextInt(2);
			switch (j){
			case 0:
				map.add(new Enemy(x0,y0,5));
				break;
			case 1:
				map.add(new Ghost(x0,y0,5));
				break;
			}
		}
		time=System.currentTimeMillis();
	}
}
    @Override
    public void draw(Graphics g) {
		BufferedImage img = null;
		 g.setColor(Color.RED);
    	 for (int i = 0; i < this.HP; i++) {
            
             g.fillRect(x + i * 6, y-10, 20, 4);
         }
    	 g.setColor(Color.gray);
    	 int remain=this.maxHP-this.HP;
    	 for (int i = 0; i < remain; i++) {
             
             g.fillRect(x + (getHP() + i) * 6, y-10, 20, 4);
         }
		switch (getHuong()) {
			case TRAI:
				try {
					img = ImageIO.read(new File("src/sprite/boss_left.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, 100, 100, null);
				break;
			case DUOI:
				try {
					img = ImageIO.read(new File("src/sprite/boss_front.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, 100, 100, null);
				break;
			case TREN:
				try {
					img = ImageIO.read(new File("src/sprite/boss_back.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, 100, 100, null);
				break;
			case PHAI:
				try {
					img = ImageIO.read(new File("src/sprite/boss_right.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, 100, 100, null);
				break;
		}
    }
@Override
	public void update() {
	
		super.update();
		Bear();
	}
}
