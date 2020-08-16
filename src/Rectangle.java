import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Point intersectionPoint;
        intersectionPoint = line.intersectionWith(new Line(getUpperLeft(), getUpperRight()));
        if (intersectionPoint != null) {
            list.add(intersectionPoint);
        }
        intersectionPoint = line.intersectionWith(new Line(getUpperLeft(), getLowerLeft()));
        if (intersectionPoint != null) {
            list.add(intersectionPoint);
        }
        intersectionPoint = line.intersectionWith(new Line(getUpperRight(), getLowerRight()));
        if (intersectionPoint != null) {
            list.add(intersectionPoint);
        }
        intersectionPoint = line.intersectionWith(new Line(getLowerLeft(), getLowerRight()));
        if (intersectionPoint != null) {
            list.add(intersectionPoint);
        }
        return list;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        Point upperRight = new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY());
        return upperRight;
    }

    /**
     * Gets lower left.
     *
     * @return the lower left
     */
    public Point getLowerLeft() {
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.getHeight());
        return lowerLeft;
    }

    /**
     * Gets lower right.
     *
     * @return the lower right
     */
    public Point getLowerRight() {
        Point lowerRight = new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY()
                + this.getHeight());
        return lowerRight;
    }

    /**
     * Reset place.
     *
     * @param x the x
     */
    public void resetPlace(int x) {
        this.getUpperLeft().setX(this.getUpperLeft().getX() + x);
    }
}