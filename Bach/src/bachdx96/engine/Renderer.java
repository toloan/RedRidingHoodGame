package bachdx96.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Phong on 4/25/2017.
 */
public class Renderer {
    private JFrame frame;
    private Map map;
    private Input input;
    JPanel panel;
    public Renderer() {
        input = new Input();
        frame = new JFrame("SimpleGame");
        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                map.draw(g);
            }
        };
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                input.press(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                input.release(e.getKeyCode());
            }
        });
    }

    public void setMap(Map map) {
        this.map = map;
        panel.setPreferredSize(map.getSize());
        frame.pack();
    }

    public void run() {
        frame.setVisible(true);
        long lastTime = System.nanoTime();
        while (frame.isVisible()) {
            long currentTime = System.nanoTime();
            if (currentTime - lastTime > 16666667L) {
                lastTime = currentTime;
                map.step(input);
                frame.repaint();
                input.step();

            }
        }
    }
}
