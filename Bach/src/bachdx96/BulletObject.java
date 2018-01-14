package bachdx96;

import bachdx96.engine.GameObject;
import bachdx96.engine.Input;

import java.awt.*;

/**
 * Created by Phong on 4/26/2017.
 */
public class BulletObject extends GameObject {
    private double speed = 0;

    public BulletObject(Point position) {
        super(position, new Rectangle(-5, -5, 10, 10), "obj_bullet");
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(position.x + boundingBox.x, position.y - boundingBox.y, boundingBox.width, boundingBox.height);
    }

    @Override
    public void onStep(Input input) {
        speed += 0.25;
        if (speed > 15) speed = 15;
        for (int i = 0; i < speed; i++) {
            position.y -= 1;
            boolean collided = false;
            java.util.List<GameObject> collideObjects = map.collide(this);
            for (GameObject o : collideObjects) {
                if (o.getType().equals("obj_enemy")) {
                    ((EnemyObject) o).setHealth(((EnemyObject) o).getHealth() - 1);
                    collided = true;
                }
            }
            if (collided) {
                map.removeGameObject(this);
                return;
            }
        }
        if (position.x < -10 || position.x > 510 || position.y < -10 || position.y > 510) {
            map.removeGameObject(this);
        }
    }
}
