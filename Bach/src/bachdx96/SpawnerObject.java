package bachdx96;

import bachdx96.engine.GameObject;
import bachdx96.engine.Input;

import java.awt.*;
import java.util.Random;

/**
 * Created by Phong on 4/26/2017.
 */
public class SpawnerObject extends GameObject {
    int timer = 0;
    Random random = new Random();
    public SpawnerObject() {
        super(new Point(), new Rectangle(), "obj_spawner");
    }

    @Override
    public void onDraw(Graphics g) {

    }

    @Override
    public void onStep(Input input) {
        timer++;
        if (timer > 80) {
            timer = 0;
            map.addGameObject(new EnemyObject(new Point(Math.abs(random.nextInt()%300)+100,0)));
        }
    }
}
