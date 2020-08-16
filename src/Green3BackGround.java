import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Green 3 back ground.
 */
public class Green3BackGround implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        int x = 50, y = 450;
        d.setColor(new Color(0x3C7F3C));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        d.fillRectangle(x, y, 100, 450);
        d.setColor(new Color(0x0C233B));

        for (int i = 0; i < 6; i++) {
            d.fillRectangle(x, y, 10, 450);
            x += 20;
        }
        x = 50;
        for (int i = 0; i < 5; i++) {
            d.fillRectangle(x, y, 100, 10);
            y += 40;
        }
        d.setColor(new Color(0x0D324D));
        d.fillRectangle(90, 390, 30, 60);
        d.setColor(new Color(0x0D4460));
        d.fillRectangle(100, 200, 10, 190);
        d.setColor(new Color(0xBFC54E));
        d.fillCircle(105, 200, 10);
        d.setColor(new Color(0xFE2512));
        d.fillCircle(105, 200, 7);
        d.setColor(Color.WHITE);
        d.fillCircle(105, 200, 3);

    }

    @Override
    public void timePassed() {
        return;
    }
}
