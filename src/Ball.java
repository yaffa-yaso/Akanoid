import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class makes a new point.
 */
public class Ball implements Sprite {
    //members
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity vel;
    private Point minPoint;
    private Point maxPoint;
    private GameEnvironment environment;

    /**
     * the constructor of ball.
     *
     * @param center          point of ball
     * @param r               radius of ball
     * @param color           color of ball
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.vel = new Velocity(0, 0);
        this.environment = gameEnvironment;
    }

    /**
     * the constructor of ball.
     *
     * @param x               the x coordinate of the intersection ball
     * @param y               the y coordinate of the intersection ball
     * @param r               radius of ball
     * @param color           color of ball
     * @param gameEnvironment the game environment
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x + r, y + r);
        this.r = r;
        this.color = color;
        this.vel = Velocity.fromAngleAndSpeed(90, 2);
        this.environment = gameEnvironment;
    }

    /**
     * boundaries the ball can move in.
     *
     * @param pointMin the lower point ball can reach
     * @param pointMax the higher point ball can reach
     */
    public void frameBoundaries(Point pointMin, Point pointMax) {
        this.minPoint = pointMin;
        this.maxPoint = pointMax;
    }

    /**
     * Gets x.
     *
     * @return x values of this ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return y values of this ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return radius of this ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets color.
     *
     * @return color of this ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), r);
    }

    /**
     * set the Velocity of this.
     *
     * @param v Velocity to set
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * Gets velocity.
     *
     * @return Velocity of this point
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * moves the ball one step.
     *
     * @param gameLevel the gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    /**
     * time Passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        CollisionInfo info;
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        info = this.environment.getClosestCollision(trajectory);
        if (info != null) {
            Rectangle rec = info.collisionObject().getCollisionRectangle();
            if (info.collisionPoint().getX() > rec.getUpperLeft().getX()
                    && info.collisionPoint().getX() < rec.getUpperRight().getX()
                    && info.collisionPoint().getY() > rec.getUpperLeft().getY()
                    && info.collisionPoint().getY() < rec.getLowerLeft().getY()) {
                this.setVelocity(new Velocity(-this.getVelocity().getDx(), -this.getVelocity().getDy()));
            } else {
                this.center = new Point(info.collisionPoint().getX(), info.collisionPoint().getY());
                if (this.getVelocity().getDx() >= 0) {
                    this.center.setX(this.center.getX() - 1);
                } else {
                    this.center.setX(this.center.getX() + 1);
                }
                if (this.getVelocity().getDy() >= 0) {
                    this.center.setY(this.center.getY() - 1);
                } else {
                    this.center.setY(this.center.getY() + 1);
                }
                if (this.center.getX() > rec.getUpperLeft().getX() && this.center.getX() < rec.getUpperRight().getX()
                        && this.center.getY() > rec.getUpperLeft().getY()
                        && this.center.getY() < rec.getLowerLeft().getY()) {
                    this.center.setY(rec.getUpperLeft().getY() - 2);
                    this.vel = Velocity.fromAngleAndSpeed(30, this.vel.getSpeed());
                    return;
                } else {
                    this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
                    return;
                }
            }
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }
}