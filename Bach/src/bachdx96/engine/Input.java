package bachdx96.engine;

import java.util.HashMap;

/**
 * Created by Phong on 4/25/2017.
 */
public class Input {
    private java.util.Map<Integer, Boolean> keyPress, keyPressed, keyReleased;

    public Input() {
        keyPress = new HashMap<>();
        keyPressed = new HashMap<>();
        keyReleased = new HashMap<>();
    }

    void press(int keyCode) {
        Boolean prev;
        if ((prev = keyPress.get(keyCode)) != null) {
            if (prev == false) {
                keyPressed.put(keyCode,true);
            }
        }
        keyPress.put(keyCode, true);
    }

    void release(int keyCode) {
        Boolean prev;
        if ((prev = keyPress.get(keyCode)) != null) {
            if (prev == true) {
                keyReleased.put(keyCode,true);
            }
        }
        keyPress.put(keyCode, false);
    }

    void step() {
        keyPressed.clear();
        keyReleased.clear();
    }

    public boolean isPress(int keyCode) {
        Boolean press = keyPress.get(keyCode);
        if (press == null) return false;
        return press;
    }

    public boolean isPressed(int keyCode) {
        Boolean pressed = keyPressed.get(keyCode);
        if (pressed == null) return false;
        return pressed;
    }

    public boolean isReleased(int keyCode) {
        Boolean released = keyReleased.get(keyCode);
        if (released == null) return false;
        return released;
    }
}
