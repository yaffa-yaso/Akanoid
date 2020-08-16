/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBall;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel   the gameLevel
     * @param removedBall the removed ball
     */
    public BallRemover(GameLevel gameLevel, Counter removedBall) {
        this.gameLevel = gameLevel;
        this.remainingBall = removedBall;
    }

    /**
     * Instantiates a new Ball remover.
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the gameLevel.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBall.decrease(1);
    }
}