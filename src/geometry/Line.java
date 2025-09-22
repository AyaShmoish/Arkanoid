// 212831325 Aya Shmoish
package geometry;

import java.util.List;

/**
 * Represents a line segment in a 2D coordinate system.
 */
public class Line {
    private final Point start;
    private final Point end;
    /**
     * Constructs a line with the specified start and end points.
     *
     * @param start the starting point of the line
     * @param end the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line with the specified coordinates.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point s2 = new Point(x2, y2);
        this.start = p1;
        this.end = s2;
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return Math.sqrt((this.start.getX() - this.end.getX()) * (this.start.getX() - this.end.getX())
                + (this.start.getY() - this.end.getY()) * (this.start.getY() - this.end.getY()));
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return  new Point(x, y);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     *  Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
      // Geometry.Line AB represented as a1x + b1y = c1
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();

        // Geometry.Line CD represented as a2x + b2y = c2
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * other.start.getX() + b2 * other.start.getY();

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            // The lines are parallel or coincident
            return  (isPointOnLine(other.start, this) || isPointOnLine(other.end, this)
                    || isPointOnLine(this.start, other) || isPointOnLine(this.end, other));
                // Lines are coincident (overlapping)
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point intersection = new Point(x, y);

            // Check if the intersection point is within the bounds of the line segments
            return (isPointOnLine(intersection, this) && isPointOnLine(intersection, other));
        }
    }


    /**
     *  Returns true if this 2 lines intersect with this line and with each other, false otherwise.
     *
     * @param other1 the first other line to check for intersection
     * @param other2 the second other line to check for intersection
     * @return true if any of the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (other1 == null || other2 == null) {
            return false;
        }
        if (this.isIntersecting(other1) && this.isIntersecting(other2) && other1.isIntersecting(other2)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the intersection point if this line intersects with the specified other line,
     * and null otherwise.
     *
     * @param other the other line to check for intersection
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        // Geometry.Line AB represented as a1x + b1y = c1
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();

        // Geometry.Line CD represented as a2x + b2y = c2
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * other.start.getX() + b2 * other.start.getY();

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            // The lines are parallel
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point intersection = new Point(x, y);

            // Check if the intersection point is within the bounds of the line segments
            if (isPointOnLine(intersection, this) && isPointOnLine(intersection, other)) {
                return intersection;
            } else {
                return null;
            }
        }
    }
    /**
     * Checks if a point is on a line segment.
     *
     * @param point the point to check
     * @param line the line to check against
     * @return true if the point is on the line segment, false otherwise
     */
    public boolean isPointOnLine(Point point, Line line) {
        return Math.min(line.start.getX(), line.end.getX()) <= point.getX()
                && point.getX() <= Math.max(line.start.getX(), line.end.getX())
                && Math.min(line.start.getY(), line.end.getY()) <= point.getY()
                && point.getY() <= Math.max(line.start.getY(), line.end.getY());
    }

    /**
     * Compares this line to the specified other line for equality.
     *
     * @param other the other line to compare to
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        // Check if both lines have the same start and end points, regardless of the order
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }
    /**
     * Returns the closest intersection point to the start of the line
     * if this line intersects with the rect rectangle. If there is no intersection, returns null.
     *
     * @param rect the rectangle to check for intersection
     * @return the closest intersection point to the start of the line, or null if no intersection occurs
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestPoint = null;
        double minDistance = Double.MAX_VALUE;
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        for (Point point : intersectionPoints) {
            if (point.getX() == rect.getUpperLeft().getX() && point.getY() == rect.getUpperLeft().getY()) {
                point = new Point(point.getX(), point.getY() + 1);
            }
           double distance = point.distance(this.start);
           if (distance < minDistance) {
               minDistance = distance;
               closestPoint = point;
           }
        }
        return closestPoint;
    }
}
