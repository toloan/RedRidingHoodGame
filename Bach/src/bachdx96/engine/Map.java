package bachdx96.engine;

import java.awt.*;
import java.util.*;

/**
 * Created by Phong on 4/25/2017.
 */
public class Map {
    private Dimension size;
    private Set<GameObject> objects, addSet, removeSet;
    public Map(Dimension size) {
        objects = new HashSet<>();
        addSet = new HashSet<>();
        removeSet = new HashSet<>();
        this.size = size;
    }

    public Dimension getSize() {
        return size;
    }

    public void addGameObject(GameObject object) {
        if (object.getMap() != null) {
            object.getMap().removeGameObject(object);
        }
        addSet.add(object);
    }

    public void removeGameObject(GameObject object) {
        removeSet.add(object);
    }

    public void draw(Graphics g) {
        for (GameObject o : objects) {
            o.onDraw(g);
        }
    }

    public void step(Input input) {
        for (GameObject o : addSet) {
            objects.add(o);
            o.setMap(this);
        }
        addSet.clear();
        for (GameObject o : removeSet) {
            objects.remove(o);
            o.setMap(null);
        }
        removeSet.clear();
        for (GameObject o : objects) {
            o.onStep(input);
        }
    }

    public java.util.List<GameObject> collide(GameObject object) {
        ArrayList<GameObject> collideObjects = new ArrayList<>();
        for (GameObject o: objects) {
            if (o==object) continue;
            if (o.collide(object)) {
                collideObjects.add(o);
            }
        }
        return collideObjects;
    }
    public java.util.List<GameObject> collide(Point point) {
        ArrayList<GameObject> collideObjects = new ArrayList<>();
        for (GameObject o: objects) {
            if (o.collide(point)) {
                collideObjects.add(o);
            }
        }
        return collideObjects;
    }

   
}
