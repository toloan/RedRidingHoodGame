package bachdx96;

import bachdx96.engine.Map;
import bachdx96.engine.Renderer;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Renderer r = new Renderer();
        Map m = new Map(new Dimension(500,500));
        PlaneObject t = new PlaneObject(new Point(250,400));
        m.addGameObject(t);
        m.addGameObject(new SpawnerObject());
        m.addGameObject(new InfoObject());
        r.setMap(m);

        r.run();
	// write your code here
    }
}
