import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int millisecondsPerFrame = 1000 / (int) this.numOfSeconds;
        long startTime = System.currentTimeMillis(); // timing
        gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText((d.getWidth() / 2) - 30, d.getHeight() / 2, this.countFrom + " ", 100);
        if (this.countFrom == 0) {
            this.stop = true;
        }
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0) {
            this.sleeper.sleepFor(milliSecondLeftToSleep);
        }
        this.countFrom -= 1;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}