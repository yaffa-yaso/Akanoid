import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private java.awt.Color color;
    private String numOfHits;
    private List<HitListener> hitListeners;

    /**
     * Instantiates a new Block.
     *
     * @param rec       the rec
     * @param color     the color
     * @param numOfHits the num of hits
     */
    public Block(Rectangle rec, java.awt.Color color, String numOfHits) {
        this.rec = rec;
        this.color = color;
        this.numOfHits = numOfHits;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * draw on.
     *
     * @param surface the DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) (rec.getUpperLeft().getX()), (int) (rec.getUpperLeft().getY()),
                (int) rec.getWidth(), (int) rec.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) (rec.getUpperLeft().getX()), (int) (rec.getUpperLeft().getY()),
                (int) rec.getWidth(), (int) rec.getHeight());
        surface.setColor(Color.WHITE);
    }

    /**
     * Gets num of hits.
     *
     * @return the num of hits
     */
    public String getNumOfHits() {
        return this.numOfHits;
    }

    /**
     * return.
     */
    public void timePassed() {
        return;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Add to gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if (collisionPoint == null) {
            return currentVelocity;
        } else {
            if (this.numOfHits == "2") {
                this.numOfHits = "1";
            } else {
                this.numOfHits = "X";
            }
        }
        if (collisionPoint.equals(this.rec.getUpperLeft()) || collisionPoint.equals(this.rec.getUpperRight())
                || collisionPoint.equals(this.rec.getLowerLeft())
                || collisionPoint.equals(this.rec.getLowerRight())) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (collisionPoint.getX() == this.rec.getUpperLeft().getX()
                || collisionPoint.getX() == this.rec.getUpperRight().getX()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify hit.
     *
     * @param hitter the hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * get hit points.
     *
     * @return this.numOfHits
     */
    public String getHitPoints() {
        return this.numOfHits;
    }
}

