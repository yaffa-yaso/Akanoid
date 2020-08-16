import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private Counter numOfLives;
    private Counter score;

    /**
     * Instantiates a new End screen.
     *
     * @param numOfLives the num of lives
     * @param score      the score
     */
    public EndScreen(Counter numOfLives, Counter score) {
        this.numOfLives = numOfLives;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.numOfLives.getValue() == 0) {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}