package game.MainControl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import game.Object.Enemy;
import game.Object.Ghost;
import game.Object.Gun;
import game.Object.Boom;
import game.Object.Boss;
import game.Object.HP;
import game.Object.Shoes;
import game.Object.Mana;
import game.Object.Coin;
import game.Object.Item;

public class Input {
    public final int GUN = 0;
    public final int BOOM = 1;
    public final int HP = 2;
    public final int SHOES = 3;
    public final int MANA = 4;
    public final int COIN = 5;

//    private int[][] map = new int[18][32];
    //    private ArrayList<Enemy> enemyxy;
//    private ArrayList<Item> itemxy;
    private FileInputStream fis;
    private int tmp;
    private int tmp2;
    private BufferedReader br;
    private ArrayList<String> Line;

    public Input(int Level) throws FileNotFoundException {
        String src = "src/map/map" + Integer.toString(Level) + ".txt";
        this.fis = new FileInputStream(src);
        br = new BufferedReader(new InputStreamReader(fis));
        Line = new ArrayList<String>();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                line = line.trim();
                Line.add(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tmp = Integer.parseInt(Line.get(18));
        tmp2 = Integer.parseInt(Line.get(19 + tmp));


    }


    public int[][] InputMap() throws IOException {

        int[][] map = new int[18][32];
        String line;
        for (int j = 0; j < 18; j++) {
            line = Line.get(j);
            if (line != null && !line.isEmpty()) {
                String[] item = line.split(" ");
                if (item[0].length() > 3)
                    item[0] = item[0].substring(2);
                for (int i = 0; i < 32; i++) {
                    item[i] = item[i].trim();
                    map[j][i] = Integer.parseInt(item[i]);

                }

            }
        }


        return map;


    }

    public ArrayList<Enemy> InputEnemy() {

        ArrayList<Enemy> enemyxy = new ArrayList<Enemy>();
        Enemy enemy = null;
        String line;

        for (int j = 0; j < tmp; j++) {
            line = Line.get(19 + j);
            if (line != null && !line.isEmpty()) {
                String[] item = line.split(" ");
                if (item[0].length() > 3)
                    item[0] = item[0].substring(2);
                for (int i = 0; i < 3; i++)
                    item[i] = item[i].trim();
                    switch(Integer.parseInt(item[0])){
                    case Enemy.ENEMY:
                    	enemy=new Enemy(50 * Integer.parseInt(item[1]), 50 * Integer.parseInt(item[2]), 3);
                    	break;
                    case Enemy.GHOST:
                    	enemy=new Ghost(50 * Integer.parseInt(item[1]), 50 * Integer.parseInt(item[2]), 3);
                    	break;
                    case Enemy.BOSS:
                    	enemy=new Boss(50 * Integer.parseInt(item[1]), 50 * Integer.parseInt(item[2]), 3);
                    	break;
                    	
                    }
                    enemyxy.add(enemy);
                }
            }
        
        //   }
        return enemyxy;


    }

    public ArrayList<Item> InputItem() {

        ArrayList<Item> itemxy = new ArrayList<Item>();

        String line;
        for (int j = 0; j < tmp2; j++) {
            line = Line.get(20 + tmp + j);
            if (line != null && !line.isEmpty()) {
                String[] item = line.split(" ");
                if (item[0].length() > 3)
                    item[0] = item[0].substring(2);
                for (int i = 0; i < 4; i++)
                    item[i] = item[i].trim();
                switch (Integer.parseInt(item[0])) {
                    case GUN:
                        Gun gun = new Gun(50 * Integer.parseInt(item[1]), 50 * Integer.parseInt(item[2]), Integer.parseInt(item[3]), null);
                        itemxy.add((Item) gun);
                        break;
                    case BOOM:
                        Boom boom = new Boom(50 * Integer.parseInt(item[1]), 50 * Integer.parseInt(item[2]), Integer.parseInt(item[3]), null);
                        itemxy.add((Item) boom);
                        break;
                    case HP:
                        HP hp = new HP(50 * Integer.parseInt(item[1]), 50 * Integer.parseInt(item[2]), Integer.parseInt(item[3]));
                        itemxy.add((Item) hp);
                        break;
                    case SHOES:
                        Shoes shoes = new Shoes(50 * Integer.parseInt(item[1]), 50 * Integer.parseInt(item[2]), Integer.parseInt(item[3]));
                        itemxy.add((Item) shoes);
                        break;
                    case MANA:
                        Mana mana = new Mana(50 * Integer.parseInt(item[1]), 50 * Integer.parseInt(item[2]), Integer.parseInt(item[3]));
                        itemxy.add((Item) mana);
                        break;
                    case COIN:
                        Coin coin = new Coin(50 * Integer.parseInt(item[1]), 50 * Integer.parseInt(item[2]), Integer.parseInt(item[3]));
                        itemxy.add((Item) coin);
                        break;
                    default:
                        break;
                }
            }
        }
        return itemxy;
    }

}
      
