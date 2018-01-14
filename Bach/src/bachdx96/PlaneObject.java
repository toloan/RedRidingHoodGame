package bachdx96;

import bachdx96.engine.GameObject;
import bachdx96.engine.Input;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Phong on 4/26/2017.
 */
public class PlaneObject extends GameObject {
    public PlaneObject(Point position) {
        super(position, new Rectangle(-10,-10,20,20), "obj_plane");
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(position.x+boundingBox.x,position.y-boundingBox.y,boundingBox.width,boundingBox.height);
    }

    @Override
    public void onStep(Input input) {
        if (input.isPress(KeyEvent.VK_LEFT)) {
            if (position.x > 0)
                position.x -= 5;
            else position.x = 0;
        }
        if (input.isPress(KeyEvent.VK_RIGHT)) {
            if (position.x < 500)
            position.x += 5;
            else position.x = 500;
        }
        if (input.isPress(KeyEvent.VK_UP)) {
            if (position.y > 0)
            position.y -= 5;
            else position.y = 0;
        }
        if (input.isPress(KeyEvent.VK_DOWN)) {
            if (position.y < 500)
            position.y += 5;
            else position.y = 500;
        }
        if (input.isPressed(KeyEvent.VK_SPACE)) {
            map.addGameObject(new BulletObject(position));
        }
    }
}
