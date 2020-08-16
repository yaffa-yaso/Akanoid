/**
 * class Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * the constructor of the new point.
     *
     * @param dx the x coordinate of the intersection Velocity
     * @param dy the y coordinate of the intersection Velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle of the ball movement in degrees
     * @param speed of the ball
     * @return Velocity velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -1 * (speed * Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt((this.getDx() * this.getDx()) + (this.getDy() * this.getDy()));
    }

    /**
     * Gets dx.
     *
     * @return dx values of this velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return dy values of this velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * set value into dx of velocity.
     *
     * @param x to set
     */
    public void setDx(double x) {
        this.dx = x;
    }

    /**
     * set value into dy of velocity.
     *
     * @param y to set
     */
    public void setDy(double y) {
        this.dy = y;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p current position of the ball
     * @return update position of the ball
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}