package game.Object;

import java.awt.Graphics;
import java.util.ArrayList;
import game.MainControl.Map;

public abstract class Weapon extends Item {
	protected int Num;// so luong giet dc
	protected int time;
	public static final  int One_One = 1;
	public static final  int MoreThanOne = 0;
	protected  int weaponType;
	public final static int GUN=1;
	public final static int BOOM=2;

	public Weapon(int x, int y, int time, Map map) {
		super(x,y);
		this.setMap(map);
		this.time = time;
		this.ItemType=WEAPON;

	}
public int getWeaponType(){
	return weaponType;
}
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int type() {
		return this.getItemType();
	}

	public abstract void Attack(Enemy enemy);

	public abstract boolean Filter(int x, int y);

	public void setInUse(MainChar main) {
		this.setX(map.getMainC().getX());
		this.setY(map.getMainC().getY());
		ArrayList<Enemy> enemies;
		if (this.time > 0)
			 {
			this.time--;
			enemies = this.getMap().kill(this);
			if (enemies != null)
				for (int i = 0; i < enemies.size(); i++)
					Attack(enemies.get(i));
		}

	}

	@Override
	public abstract void draw(Graphics g);

	public int getNum() {
		return this.Num;
	}
	@Override
	public void update() {
	}
}
