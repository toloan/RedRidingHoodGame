package game.Object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mana extends Supply{

	public Mana(int x, int y, int supply) {
		super(x, y, supply);
	}

	@Override
	public void draw(Graphics g) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/sprite/mana.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, this.getX(), this.getY(), 25, 25, null);
		}
		@Override
		public void setInUse(MainChar main) {
			super.setInUse(main);
			main.plusMana(supply);;		
			//	main.destroyWeapon(this);
			
		}

		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}

		

}
