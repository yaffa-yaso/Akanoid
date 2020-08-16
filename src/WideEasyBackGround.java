import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Wide easy back ground.
 */
public class WideEasyBackGround implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0xE9ED6E));
        int x = 0, y = 270;
        while (x < 800) {
            d.drawLine(100, 160, x, y);
            x += 10;
        }
        d.fillCircle(100, 160, 60);
        d.setColor(new Color(0xECDB22));
        d.fillCircle(100, 160, 50);
        d.setColor(new Color(0xFFEC03));
        d.fillCircle(100, 160, 40);
    }

    @Override
    public void timePassed() {
        return;
    }
}
