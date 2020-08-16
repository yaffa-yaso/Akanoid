/**
 * The type Printing hit listener.
 */
public class PrintingHitListener implements HitListener {
    /**
     * hit event.
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}