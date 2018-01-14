package game.Object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Coin extends Supply {

	public Coin(int x, int y, int supply) {
		super(x, y, supply);

	}

	@Override
	public void draw(Graphics g) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/sprite/coin.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, this.getX(), this.getY(), 25,25, null);
	}


	@Override
	public void setInUse(MainChar main) {
		super.setInUse(main);
		 main.setCoin(main.getCoin()+supply);
		 

	}

	@Override
	public void update() {
		super.update();
		
	}

}
