import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Direct hit back ground.
 */
public class DirectHitBackGround implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 160, 40);
        d.drawCircle(400, 160, 80);
        d.drawCircle(400, 160, 120);
        d.drawLine(420, 160, 560, 160);
        d.drawLine(380, 160, 240, 160);
        d.drawLine(400, 140, 400, 0);
        d.drawLine(400, 180, 400, 320);
    }

    @Override
    public void timePassed() {
        return;
    }
}
