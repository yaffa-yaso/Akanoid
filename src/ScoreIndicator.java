import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle rec;
    private java.awt.Color color;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scores the scores
     */
    public ScoreIndicator(Counter scores) {
        this.score = scores;
        this.color = Color.WHITE;
        this.rec = new Rectangle(new Point(0, 0), 800, 20);
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
                "score : " + score.getValue(), 12);
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
