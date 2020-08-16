import java.util.List;

/**
 * this class create a new Line.
 */
public class Line {

    private Point start;
    private Point end;

    /**
     * the constructor of the new point.
     *
     * @param start the star point of the intersection line
     * @param end   the end coordinate of the intersection line
     */
    public Line(Point start, Point end) {

        Point p1 = new Point(start.getX(), start.getY());
        Point p2 = new Point(end.getX(), end.getY());

        if (start.getX() <= end.getX()) {
            this.start = p1;
            this.end = p2;
        } else {
            this.start = p2;
            this.end = p1;
        }
    }

    /**
     * the constructor of the new point.
     *
     * @param x1 the x coordinate of the star point of the intersection line
     * @param y1 the y coordinate of the star coordinate of the intersection line
     * @param x2 the x coordinate of the end point of the intersection line
     * @param y2 the y coordinate of the end coordinate of the intersection line
     */
    public Line(double x1, double y1, double x2, double y2) {

        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);
        if (x1 <= x2) {
            this.start = point1;
            this.end = point2;
        } else {
            this.start = point2;
            this.end = point1;
        }
    }

    /**
     * Length double.
     *
     * @return the length of this line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Middle point.
     *
     * @return the middle point of this line
     */
    public Point middle() {
        double xm = (start.getX() + end.getX()) / 2;
        double ym = (start.getY() + end.getY()) / 2;
        return new Point(xm, ym);
    }

    /**
     * Start point.
     *
     * @return the start point of this line
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of this line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Slope double.
     *
     * @return the slope of this line
     */
    public double slope() {

        if (this.start.getX() == this.end.getX()) {
            return Double.POSITIVE_INFINITY;
        } else {
            double dx = this.start.getX() - this.end.getX();
            double dy = this.start.getY() - this.end.getY();
            return dy / dx;
        }
    }

    /**
     * Is intersecting boolean.
     *
     * @param other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * Intersection with point.
     *
     * @param other line
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // if the line are the same there is no need to test them
        if (this.equals(other)) {
            return null;
        }
        double m1 = this.slope();
        double m2 = other.slope();
        double inf = Double.POSITIVE_INFINITY;

        if (m1 == m2) {
            return null;
        }

        if (m1 == inf) {
            if (((other.start.getX() <= this.start.getX()) && (other.end.getX() >= this.start.getX()))
            || ((other.end.getX() <= this.start.getX()) && (other.start.getX() >= this.start.getX()))) {
                double maxOther = Math.max(other.start.getY(), other.end.getY());
                double minOther = Math.min(other.start.getY(), other.end.getY());
                double maxThis = Math.max(this.start.getY(), this.end.getY());
                double minThis = Math.min(this.start.getY(), this.end.getY());
                if (minOther >= minThis && maxOther <= maxThis) {
                    double x = this.start.getX();
                    double y = m2 * (x - other.start.getX()) + other.start().getY();
                    return new Point(x, y);
                }
            }
            return null;
        }
        if (m2 == inf) {
            if (((this.start.getX() <= other.start.getX()) && (this.end.getX() >= other.start.getX()))
                || ((this.end.getX() <= other.start.getX()) && (this.start.getX() >= other.start.getX()))) {
                double maxThis = Math.max(this.start.getY(), this.end.getY());
                double minThis = Math.min(this.start.getY(), this.end.getY());
                double maxOther = Math.max(other.start.getY(), other.end.getY());
                double minOther = Math.min(other.start.getY(), other.end.getY());
                if (minThis >= minOther && maxThis <= maxOther) {
                    double x = other.start.getX();
                    double y = m1 * (x - this.start.getX()) + this.start().getY();
                    return new Point(x, y);
                }
            }
            return null;
        }

        if (m1 == 0) {
            if (((other.start.getX() >= this.start.getX()) && (other.end.getX() <= this.end.getX()))
                    || ((other.end.getX() >= this.start.getX()) && (other.start.getX() <= this.end.getX()))) {
                double y = this.start.getY();
                double min = Math.min((other.start.getY()), other.end.getY());
                double max = Math.max((other.start.getY()), other.end.getY());
                if (min <= y && max >= y) {
                    double x = other.start.getX() + ((y - other.start.getY()) / m2);
                    return new Point(x, y);
                }
                return null;
            }
        }

        if (m2 == 0) {
            if (((this.start.getX() >= other.start.getX()) && (this.end.getX() <= other.end.getX()))
                    || ((this.end.getX() >= other.start.getX()) && (this.start.getX() <= other.end.getX()))) {
                double y = other.start.getY();
                double min = Math.min((this.start.getY()), this.end.getY());
                double max = Math.max((this.start.getY()), this.end.getY());
                if (min <= y && max >= y) {
                    double x = this.start.getX() + ((y - this.start.getY()) / m1);
                    return new Point(x, y);
                }
            }
            return null;
        }

        double x = ((m1 * this.start.getX()) - this.start.getY() - (m2 * other.start.getX()) + other.start.getY())
                / (m1 - m2);

        if (Math.min(this.start.getX(), this.end.getX()) <= x && x <= Math.max(this.end.getX(), this.end.getX())) {
            if ((Math.min(other.start.getX(), other.end.getX()) <= x
                    && x <= Math.max(other.end.getX(), other.end.getX()))) {
                double y = (m2 * (x - other.start.getX())) + other.start.getY();
                return new Point(x, y);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Equals boolean.
     *
     * @param other line to compare with this line
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {

        if ((this.start == other.start && this.end == other.end)
                || (this.start == other.end && this.end == other.start)) {
            return true;
        }
        return false;
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point point;
        List<Point> list = rect.intersectionPoints(this);
        if (list.size() == 0) {
            return null;
        }
        int i = 0;
        point = list.get(i);
        double tempD, minD = this.start.distance(list.get(i));
        for (i = 1; i < list.size(); i++) {
            tempD = this.start.distance(list.get(i));
            if (tempD < minD) {
                point = list.get(i);
                minD = tempD;
            }
        }
       return point;
    }

}