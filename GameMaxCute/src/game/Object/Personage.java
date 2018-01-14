/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Object;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author QuangDai
 */
public abstract class Personage extends GameObject {
    protected int HP;
    protected int maxHP;
    protected int huong;
    protected int speed;

    protected int STEP;

    public Personage(int x, int y, int blood) {
        super(x, y);
        this.maxHP = blood;
        this.HP = blood;
        STEP=50;
    }

    public Personage(int x, int y) {
        super(x, y);
        this.HP = 10;
        this.maxHP = 10;
        STEP =50;
    }

    public void minushp(int x) {
        if (HP > 0)
            HP -= x;
        else
            HP = 0;
    }

    public void plushp(int x) {
        HP += x;
        if (HP > maxHP)
            HP = maxHP;
    }
    


    public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getHuong() {
        return huong;
    }

    public void setHuong(int huong) {
        this.huong = huong;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int Blood) {
        this.HP = Blood;

    }

    public void up() {
        if (y >= MAP_UP + speed) {
            if (map.Where(x, y - speed) == 1 && map.Where(x+STEP-1,y-speed)==1|| 
            	map.Where(x, y - speed) == 4 && map.Where(x+STEP-1,y-speed)==4|| 
            	map.Where(x, y - speed) == 6 && map.Where(x+STEP-1,y-speed)==6) {
                y -= speed;
                return;
            } else huong = PHAI;
        } else y = MAP_UP;
    }

    public void down() {
        if (y <= MAP_DOWN - speed - STEP) {
            if (map.Where(x, y + speed + STEP - 1) == 1 && map.Where(x+STEP-1,y+speed+STEP-1)==1|| 
            	map.Where(x, y + speed + STEP - 1) == 4 && map.Where(x+STEP-1,y+speed+STEP-1)==4|| 
            	map.Where(x, y + speed + STEP - 1) == 6 && map.Where(x+STEP-1,y+speed+STEP-1)==6) {
                y += speed;
                return;
            } else huong = TRAI;
        } else y = MAP_DOWN - STEP;
    }

    public void right() {
        if (x <= MAP_RIGHT - speed - STEP) {
            if ((map.Where(x + speed + STEP - 1, y) == 1 ||map.Where(x + speed + STEP - 1, y) == 4||map.Where(x + speed + STEP - 1, y) == 6)&& 
               (map.Where(x+speed+STEP-1,y+STEP-1)==1||  map.Where(x+speed+STEP-1,y+STEP-1)==4|| map.Where(x+speed+STEP-1,y+STEP-1)==6)) {
                x += speed;
                return;
            } else huong = DUOI;
        } else x = MAP_RIGHT - STEP;
    }

    public void left() {
        if (x >= MAP_LEFT + speed) {
            if ((map.Where(x - speed, y) == 1||map.Where(x - speed, y) == 4||map.Where(x - speed , y) == 6) &&(map.Where(x-speed,y+STEP-1)==1 || 
            	map.Where(x-speed,y+STEP-1)==4|| 
            	map.Where(x-speed,y+STEP-1)==6)) {
                x -= speed;
                return;
            } else huong = TREN;
        } else x = MAP_LEFT;
    }


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
    }


    public boolean isDie() {
        return HP <= 0;
    }
}
