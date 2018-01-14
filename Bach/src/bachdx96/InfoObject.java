package bachdx96;

import bachdx96.engine.GameObject;
import bachdx96.engine.Input;

import java.awt.*;

/**
 * Created by Phong on 4/26/2017.
 */
public class InfoObject extends GameObject {
    public InfoObject() {
        super(new Point(), new Rectangle(), "obj_info");
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(Color.black);
        g.drawString("Movement: Arrow keys",200,200);
        g.drawString("Fire: Space bar",200,250);
    }

    @Override
    public void onStep(Input input) {

    }
}
