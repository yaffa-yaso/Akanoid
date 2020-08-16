/**
 * this class create a new point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * the constructor of the new point.
     *
     * @param x the x coordinate of the intersection point
     * @param y the y coordinate of the intersection point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other a point to check distance with
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double distance = ((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y));
        distance = Math.sqrt(distance);

        return distance;
    }

    /**
     * Equals boolean.
     *
     * @param other a point to compare to this point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return ((int) this.x == (int) other.x) && ((int) this.y == (int) other.y);
    }

    /**
     * Gets x.
     *
     * @return x values of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return y values of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * set value into x of this.
     *
     * @param x1 to set
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * set value into y of this.
     *
     * @param y1 to set
     */
    public void setY(double y1) {
        this.y = y1;
    }
}