package bachdx96;

import bachdx96.engine.GameObject;
import bachdx96.engine.Input;

import java.awt.*;

/**
 * Created by Phong on 4/26/2017.
 */
public class EnemyObject extends GameObject {
    private int health = 3;
    private double speed = 0;
    public EnemyObject(Point position) {
        super(position, new Rectangle(-10, -10, 20, 20), "obj_enemy");
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(position.x + boundingBox.x, position.y - boundingBox.y, boundingBox.width, boundingBox.height);
        g.drawString("Health: "+ health,position.x,position.y - 10);
    }

    @Override
    public void onStep(Input input) {
        speed += 0.05;
        if (speed>3)speed = 3;
        if (health <=0) map.removeGameObject(this);
        position.y +=speed;
        if (position.x < -10 || position.x > 510 || position.y < -10 || position.y > 510) {
            map.removeGameObject(this);
        }
    }
}
