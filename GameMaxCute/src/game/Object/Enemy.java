package game.Object;
import game.Object.Personage;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy extends Personage {
    protected int power;
    private int width;
    private int centerX;
    private int centerY;
    private int character_locate;
    public final static int ENEMY=0;
    public final static int GHOST=1;
    public final static int BOSS=2;

    public Enemy(int x, int y, int blood) {
        super(x, y, blood);
        Random random=new Random();
        power = 1;
        width = 4;
        speed = 1;
        centerX=x;
        centerY=y;
        huong = random.nextInt(4)+1;
        character_locate =0;
    }

    public Enemy(int x, int y) {
        super(x, y);
        Random random=new Random();
        power = 1;
        width = 4;
        speed = 1;
        centerX=x;
        centerY=y;
        huong = random.nextInt(4)+1;
        character_locate =0;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        BufferedImage img = null;
        switch (getHuong()) {
            case TRAI:
                try {
                    img = ImageIO.read(new File("src/sprite/wolf2_left.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(img, x, y, 50, 50, null);
                break;
            case DUOI:
                try {
                    img = ImageIO.read(new File("src/sprite/wolf2_front.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(img, x, y, 50, 50, null);
                break;
            case TREN:
                try {
                    img = ImageIO.read(new File("src/sprite/wolf2_back.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(img, x, y, 50, 50, null);
                break;
            case PHAI:
                try {
                    img = ImageIO.read(new File("src/sprite/wolf2_right.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(img, x, y, 50, 50, null);
                break;
        }

    }
 
    @Override
    public void up()
    {   	
    	Random random=new Random();
    	if(y <= centerY - 4*STEP||y==MAP_UP){huong = random.nextInt(4)+1 ;}
    	else
    		if(y>=MAP_UP + speed)
    	    {
    	   	if(map.Where(x+1,y-speed)==1&&map.Where(x+STEP-1,y-speed)==1)
    		  {y-=speed;return;}
    	   	else huong = random.nextInt(4)+1;
    	    }
    	   	else y=MAP_UP;
    }
    @Override
    public void down()
    {
    	Random random=new Random();
    	if(y >= centerY+4*STEP-STEP||y==MAP_DOWN-STEP){huong = random.nextInt(4)+1;}
    	else
    	 	if(y <= MAP_DOWN - speed-STEP)
    	   	{
    	   	if(map.Where(x+1,y+speed+STEP)==1&&map.Where(x+STEP-1,y+speed+STEP)==1)
    		  {y+=speed;return;}
    	   	else huong = random.nextInt(4)+1;
    	   	}
    	   	else y= MAP_DOWN - STEP;
    }
    @Override
    public void left()
    {
    	Random random=new Random();
    	if(x <= centerX-4*STEP||x==MAP_LEFT){huong = random.nextInt(4)+1;}
    	else
    		if(x>= MAP_LEFT + speed)
    	   	{
    	   	if(map.Where(x-speed,y+1)==1&&map.Where(x-speed,y+STEP-1)==1)
    		  {x-=speed;return;}
    	   	else huong = random.nextInt(4)+1;
    	   	}
    	   	else x=MAP_LEFT;
    		

    }
    @Override
    public void right()
    {
    	Random random=new Random();
    	if(x>= centerX+4*STEP- STEP||x==MAP_RIGHT-STEP){huong = random.nextInt(4)+1;}
    	else 
    	if(x <= MAP_RIGHT - speed -STEP)
	  	{
	   	if(map.Where(x+speed+STEP,y+1)==1&&map.Where(x+speed+STEP,y+STEP-1)==1)
		  {x+=speed;return;}
	   	else 
	   		huong = random.nextInt(4)+1;
	  	}
	  	else x=MAP_RIGHT-STEP;
    }

    public void run(){
            checkAttack();           
            if(character_locate==0)
            switch (huong) {
			    case TRAI:
				  left();
				break;
               case PHAI:
				  right();
				break;
				case TREN:
			      up();
				break;
				case DUOI:
				  down();	
				break;
			    default:
				break;
			}
            else
            {
           	 switch (character_locate) {
			    case TRAI:
			      centerX= x;
			      centerY= y;
				  left();
				break;
                case PHAI:
                  centerX=x;
                  centerY=y;
				  right();
				break;
				case TREN:
				  centerX=x;
				  centerY=y;
			      up();
				break;
				case DUOI:
				  centerX=x;
				  centerY=y;
				  down();	
				break;
			    default:
				break;
           	 }
            }
   }
    public void attack(){
    	   getMap().getMainC().minushp(power);
    }
    public void checkAttack() {
    	 int mainX =getMap().getMainC().getX();
         int mainY = getMap().getMainC().getY();
         if ((mainX == this.x) && (mainY > this.y - width && mainY < this.y + width)||(mainY == this.y) && (mainX > this.x - width && mainX < this.x + width)) {
             attack();
             System.out.println(getMap().getMainC().getHP());
         }
         int j=0;
         if((mainX == this.x) && (mainY > this.y - 200 )&& (mainY < this.y ))
         {
         	j = TREN;
         }
         if((mainX == this.x) && (mainY < this.y + 200 )&& (mainY > this.y ))
         {
         	j = DUOI;
         }
         if((mainY == this.y) && (mainX > this.x - 200 )&& (mainX < this.x ))
         {
         	j = PHAI;
         }
         if((mainY == this.y) && (mainX > this.x - 200 )&& (mainX < this.x ))
         {
         	j = TRAI;
         }
         character_locate = j;
         if(character_locate !=0)huong=character_locate;
     }
    
@Override
	public void update() {
		run();
	}
}
