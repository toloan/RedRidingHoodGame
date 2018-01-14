package game.Object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shoes extends Support {
	public Shoes(int x, int y, int Ability) {
		super(x, y, Ability);
		// TODO Auto-generated constructor stub
	}

	//private MainChar main;


	 @Override
	public void setInUse(MainChar main) {
		 super.setInUse(main);
			if(this.isInUse()==true){
			main.setSpeed(main.getSpeed()+Ability);
			System.out.println("Main: "+ main.getSpeed());
			}
			else 
				main.setSpeed(main.getSpeed()-Ability);
			System.out.println("Main: "+ main.getSpeed());
	}
	 public int getSupportType()//thêm
		{
			return SHOES;
		}
	@Override
	public void draw(Graphics g) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/sprite/shoes.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, this.getX(), this.getY(), 25, 25, null);



	}

	@Override
	public void update() {
		
	}
}
