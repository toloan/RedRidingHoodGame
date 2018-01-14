package game.MainControl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Render {
    private Map map;
    private final int HEIGH_LEN = 18;
    private final int WIDTH_LEN = 32;// do dai mang map
    private final int BIT_LEN = 50;// do dai 1 o
    private final int SEA = 0;
    private final int LAND = 1;
    private final int DOOR = 4;
    private final int TREE = 2;
    private final int HOUSE = 5;
    private final int BRIDGE = 6;
    private final int BED = 7;
    private final int SHELF = 8;
    private String src;
    private BufferedImage img;

    public Render(int level, Map map) {
        img = null;
        src = "src/sprite/" + Integer.toString(level) + "/";
        this.map = map;
    }

    public void drawMap(Graphics g) {
        int i;
        int j;
        for (i = 0; i < HEIGH_LEN; i++) {
            for (j = 0; j < WIDTH_LEN; j++) {
                switch (map.getMap()[i][j]) {
                    case DOOR:
                        try {
                            img = ImageIO.read(new File(src + "door.png"));
                        } catch (IOException e) {
                       //     e.printStackTrace();
                        }
                        g.drawImage(img, BIT_LEN * j, +BIT_LEN * i, null);
                        break;
                    case LAND:
                        try {
                            img = ImageIO.read(new File(src + "land.png"));
                        } catch (IOException e) {
                      //      e.printStackTrace();
                        }
                        g.drawImage(img, BIT_LEN * j, +BIT_LEN * i, null);
                        break;
                    case SEA:
                        try {
                            img = ImageIO.read(new File(src + "water.png"));
                        } catch (IOException e) {
                     //       e.printStackTrace();
                        }
                        g.drawImage(img, BIT_LEN * j, +BIT_LEN * i, null);
                        break;
                    case TREE:
                        try {
                            img = ImageIO.read(new File(src + "tree.png"));
                        } catch (IOException e) {
                        //   e.printStackTrace();
                        }
                        g.drawImage(img, BIT_LEN * j, +BIT_LEN * i, null);
                        break;
                    case HOUSE:
                        try {
                            img = ImageIO.read(new File(src + "house.png"));
                        } catch (IOException e) {
                        //    e.printStackTrace();
                        }
                        g.drawImage(img, BIT_LEN * j, +BIT_LEN * i, null);
                        break;
                    case BRIDGE:
                        try {
                            img = ImageIO.read(new File(src + "bridge.png"));
                        } catch (IOException e) {
                        //    e.printStackTrace();
                        }
                        g.drawImage(img, BIT_LEN * j, +BIT_LEN * i, null);
                    case BED:

                        try {
                            img = ImageIO.read(new File(src + "bed.png"));
                        } catch (IOException e) {
                        //    e.printStackTrace();
                        }

                        g.drawImage(img, BIT_LEN * j, +BIT_LEN * i, null);
                    case SHELF:
                        try {
                            img = ImageIO.read(new File(src + "shelf.png"));
                        } catch (IOException e) {
                           //e.printStackTrace();
                        }
                        g.drawImage(img, BIT_LEN * j, +BIT_LEN * i, null);
                    default:
                        break;
                }
            }
        }

    }

    public void drawMain(Graphics g) {
        if (map.getMainC() != null)
            map.getMainC().draw(g);
    }

    public void drawItem(Graphics g) {
        g.setColor(Color.ORANGE);
        for (int k = 0; k < map.getListItem().size(); k++)
            map.getListItem().get(k).draw(g);
    }



    public void drawEnemy(Graphics g) {
        for (int k = 0; k < map.getEnemy().size(); k++)
            map.getEnemy().get(k).draw(g);
    }

    public void drawPhamVi(Graphics g) {
        if (map.getShape() != null) {
g.drawOval((int) (map.getMainC().getX()-(int) ((Ellipse2D) map.getShape()).getHeight()/2), (int) map.getMainC().getY()-(int) ((Ellipse2D) map.getShape()).getHeight()/2,(int) ((Ellipse2D) map.getShape()).getHeight(), (int) ((Ellipse2D) map.getShape()).getWidth());
            
        }
    }

    public void draw(Graphics g) {
        drawMap(g);
        drawMain(g);
        drawItem(g);
        drawEnemy(g);
        drawPhamVi(g);
//        if(map.getBullet()!=null)
//        map.getBullet().draw(g);
        if(map.getBullet()!=null)
    	for(int i=0;i<map.getBullet().size();i++)
    		map.getBullet().get(i).draw(g);;
    }
}

