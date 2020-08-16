import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Final four back ground.
 */
public class FinalFourBackGround implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Color[] colors = new Color[3];
        colors[0] = new Color(0xD2DCD1);
        colors[1] = new Color(0xB9C3B8);
        colors[2] = new Color(0xAAB4A9);
        int x = 130, y = 420, r = 25;
        d.setColor(new Color(0x28B1BC));
        d.fillRectangle(0, 0, 800, 600);

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 10; i++) {
                d.setColor(Color.WHITE);
                d.drawLine((x - 30) + (10 * i), y, (x - 60) + (10 * i), 600);
            }
            d.setColor(colors[0]);
            for (int i = 0; i < 2; i++) {
                d.fillCircle(x, y, r);
                x += 30;
                r -= 5;
                d.setColor(colors[2]);
            }
            y -= 20;
            x -= 90;
            r = 20;
            for (int i = 0; i < 3; i++) {
                d.setColor(colors[i]);
                d.fillCircle(x, y, r);
                x += 40;
                if (i == 0) {
                    y -= 10;
                    r += 7;
                } else {
                    y += 10;
                    r += 3;
                }
            }
            x = 600;
            y = 500;
        }
    }

    @Override
    public void timePassed() {
        return;
    }
}
