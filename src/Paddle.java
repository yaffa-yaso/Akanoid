import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private java.awt.Color color;
    private int paddleSpeed;
    private int minX;
    private int maxX;
    private double region1;
    private double region2;
    private double region3;
    private double region4;
    private double region5;
    private double region6;

    /**
     * Instantiates a new Paddle.
     *
     * @param rec      the rec
     * @param color    the color
     * @param keyboard the keyboard
     * @param paddleSpeed the paddle's Speed
     */
    public Paddle(Rectangle rec, java.awt.Color color, biuoop.KeyboardSensor keyboard, int paddleSpeed) {
        this.rec = rec;
        this.color = color;
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
        this.region1 = this.rec.getUpperLeft().getX();
        this.region2 = (this.rec.getUpperLeft().getX() + (0.20 * (rec.getWidth())));
        this.region3 = (this.rec.getUpperLeft().getX() + (0.40 * (rec.getWidth())));
        this.region4 = (this.rec.getUpperLeft().getX() + (0.60 * (rec.getWidth())));
        this.region5 = (this.rec.getUpperLeft().getX() + (0.80 * (rec.getWidth())));
        this.region6 = this.rec.getUpperRight().getX();
    }

    /**
     * Frame boundaries.
     *
     * @param min the min
     * @param max the max
     */
    public void frameBoundaries(int min, int max) {
        this.minX = min;
        this.maxX = max;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (this.rec.getUpperLeft().getX() != minX) {
            this.rec.resetPlace(-this.paddleSpeed);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (this.rec.getUpperRight().getX() != maxX) {
            this.rec.resetPlace(this.paddleSpeed);
        }
    }

    /**
     * time Passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * draw On.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) (rec.getUpperLeft().getX()), (int) (rec.getUpperLeft().getY()),
                (int) rec.getWidth(), (int) rec.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) (rec.getUpperLeft().getX()), (int) (rec.getUpperLeft().getY()),
                (int) rec.getWidth(), (int) rec.getHeight());
    }

    /**
     * get Collision Rectangle.
     *
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }
    /**
     * hit.
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter the hitter ball
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null) {
            return currentVelocity;
        } else {
            if (collisionPoint.getY() == rec.getUpperLeft().getY()
                    || collisionPoint.getY() == rec.getLowerLeft().getY()) {
                if (collisionPoint.getX() >= this.region1 && collisionPoint.getX() < this.region2) {
                    return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                } else if (collisionPoint.getX() >= this.region2
                        && collisionPoint.getX() < this.region3) {
                    return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                } else if (collisionPoint.getX() >= this.region4
                        && collisionPoint.getX() < this.region5) {
                    return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                } else if (collisionPoint.getX() >= this.region5
                        && collisionPoint.getX() < this.region6) {
                    return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
                } else {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
            } else {
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            }
        }
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }
}