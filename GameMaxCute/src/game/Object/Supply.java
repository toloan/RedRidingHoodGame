package game.Object;

import java.awt.Graphics;

public abstract class Supply extends Item {
	protected int supply;
	public Supply(int x, int y,int supply) {
		super(x,y);
		this.supply=supply;
		ItemType=SUPPLY;
	}
	public int type() {
		return this.getItemType();
	}
	@Override
	public abstract void draw(Graphics g);
	@Override
	public void setInUse(MainChar main) {
		super.setInUse(main);
		getMap().remove(this);
	}
}
