package game.Object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Armor extends Support {
	private int oldBlood;
	public Armor(int x, int y, int Ability) {
		super(x, y, Ability);
		
	}
	
	 @Override
	public void setInUse(MainChar main) {
		super.setInUse(main);
		if(this.isInUse()==true){
			oldBlood=main.getHP();
			main.setHP(main.getHP()+Ability);
			}
			else {
					main.setHP(oldBlood);
					Ability=Ability-oldBlood;
				
			}
	}
	 public int getSupportType()//thêm
		{
			return ARMOR;
		}
	@Override
	public void draw(Graphics g) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/sprite/armor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, this.getX(), this.getY(), 25,25, null);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


}
