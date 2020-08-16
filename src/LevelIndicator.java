import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level indicator.
 */
public class LevelIndicator implements Sprite {
    private String level;
    private Rectangle rec;
    private java.awt.Color color;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param level the num of lives
     */
    public LevelIndicator(String level) {
        this.level = level;
        this.color = Color.WHITE;
        this.rec = new Rectangle(new Point(650, 0), 150, 20);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) (rec.getUpperLeft().getX()), (int) (rec.getUpperLeft().getY()),
                (int) rec.getWidth(), (int) rec.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) (rec.getUpperLeft().getX()), (int) (rec.getUpperLeft().getY()),
                (int) rec.getWidth(), (int) rec.getHeight());
        d.setColor(Color
                .BLACK);
        d.drawText((int) (this.rec.getUpperLeft().getX() + (this.rec.getWidth() / 2) - 70),
                (int) (this.rec.getUpperRight().getY() + (this.rec.getHeight() / 2) + 3),
                "Level : " + level, 12);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
