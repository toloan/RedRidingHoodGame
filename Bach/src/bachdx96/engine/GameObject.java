package bachdx96.engine;

import java.awt.*;

/**
 * Created by Phong on 4/25/2017.
 */
public abstract class GameObject {

    protected Point position;
    protected Rectangle boundingBox;
    protected Map map;

    public String getType() {
        return type;
    }

    protected String type;
    public GameObject(Point position, Rectangle boundingBox, String type) {
        this.position = (Point) position.clone();
        this.boundingBox = (Rectangle) boundingBox.clone();
        this.type = type;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = (Rectangle) boundingBox.clone();
    }
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean collide(GameObject other) {
        Rectangle rect1 = (Rectangle) boundingBox.clone();
        rect1.x += position.x;
        rect1.y += position.y;
        Rectangle rect2 = (Rectangle) other.boundingBox.clone();
        rect2.x += other.position.x;
        rect2.y += other.position.y;
        return rect1.intersects(rect2);
    }
    public boolean collide(Point point) {
        Rectangle rect1 = (Rectangle) boundingBox.clone();
        rect1.x += position.x;
        rect1.y += position.y;
        return rect1.contains(point);
    }

    public abstract void onDraw(Graphics g);

    public abstract void onStep(Input input);
}
