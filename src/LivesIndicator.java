import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Counter numOfLives;
    private Rectangle rec;
    private java.awt.Color color;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param numOfLives the num of lives
     */
    public LivesIndicator(Counter numOfLives) {
        this.numOfLives = numOfLives;
        this.color = Color.WHITE;
        this.rec = new Rectangle(new Point(0, 0), 100, 20);
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
        d.drawText((int) (this.rec.getUpperLeft().getX() + (this.rec.getWidth() / 2) - 1),
                (int) (this.rec.getUpperRight().getY() + (this.rec.getHeight() / 2) + 3),
                "Lives : " + numOfLives.getValue(), 12);
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
