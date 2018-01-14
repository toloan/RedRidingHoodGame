package game.Object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HP extends Supply {

	public HP(int x, int y, int supply) {
		super(x, y, supply);
	}

	@Override
	public void draw(Graphics g) {
		BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/sprite/hp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, this.getX(), this.getX(), 25,25, null);

		}
		@Override
		public void setInUse(MainChar main) {
			super.setInUse(main);
			main.plushp(this.supply);	
			
		}

		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
		
	}

