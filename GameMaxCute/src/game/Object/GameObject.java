package game.Object;

import java.awt.Graphics;

import game.MainControl.Map;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected Map map;
    protected static int MAP_LEFT = 0;// mÃ©p trÃ¡i
    protected static int MAP_RIGHT = 1600;// mÃ©p pháº£i
    protected  static int MAP_UP = 0;// mÃ©p trÃªn
    protected static int MAP_DOWN = 900;// mÃ©p dÆ°á»›i
    public final static int PHAI = 2;
    public final static int TRAI = 4;
    public final static int TREN = 1;
    public final static int DUOI = 3;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public GameObject(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public abstract void update();

    public abstract void draw(Graphics g);
}
