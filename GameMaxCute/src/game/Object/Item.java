package game.Object;

import java.awt.Graphics;
public abstract class Item extends GameObject{
	
	public final int WEAPON=0;
	public final int SUPPORT=1;
	public final int SUPPLY=2;
	protected int x;
	protected int ItemType;// co gia tri WEAPON< ARMOR<SUPPLY
	protected int y;
	protected boolean inUse;// neu nguoi dung doi vu khi thi chuyen sang true, mac dinh la false
	public Item(int x, int y) {
		super(x,y);
		this.inUse=false;
	}

	public int getItemType() {
		return ItemType;
	}
	 public void setItemType(int i)
		{
			ItemType = i;
		}
	public boolean isInUse() {	
		return inUse;
	}

	public void setInUse(MainChar main) {
	 if(this.inUse==true)
		 this.inUse=false;
	 else{
		 this.inUse=true;
	 }	 
	}
	// giai thich: 
	//WEAPON: setinUse -> beingUsed
	//ARMOR: setinUse
	//SUPPLY: setinUse
	@Override
	public void update() {
	}
	public abstract void draw(Graphics g);
	
}
