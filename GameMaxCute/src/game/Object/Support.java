package game.Object;

public abstract class Support extends Item {
	protected int Ability;
	protected int SupportType;
	public int SHOES=1;//thêm
    public int ARMOR =2;//thêm
	//protected MainChar main;

	public Support(int x, int y, int Ability) {
		super(x,y);
		this.Ability=Ability;
		this.ItemType=SUPPORT;//TODO:sửa
	}
	public abstract int getSupportType();
	
	public int getAbility()//thêm
	{
		return Ability;
	}
	public void setAbility(int i)//thêm
	{
		Ability = i;
	}
	 @Override
	public void setInUse(MainChar main) {
		super.setInUse(main);
	//	this.setMain(main);
	} 
}
