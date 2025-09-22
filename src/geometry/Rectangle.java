// 212831325 Aya Shmoish
package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle in a 2D coordinate system.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Creates a new rectangle with location and width/height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line to check for intersections
     * @return a list of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        ArrayList<Point> points = new ArrayList<>();
        if (this.getTop().isIntersecting(line)) {
            if (this.getTop().intersectionWith(line) != null) {
                points.add(this.getTop().intersectionWith(line));
            }
        }
        if (this.getBottom().isIntersecting(line)) {
            if (this.getBottom().intersectionWith(line) != null) {
                points.add(this.getBottom().intersectionWith(line));
            }
        }
        if (this.getLeft().isIntersecting(line)) {
            if (this.getLeft().intersectionWith(line) != null) {
                points.add(this.getLeft().intersectionWith(line));
            }
        }
        if (this.getRight().isIntersecting(line)) {
            if (this.getRight().intersectionWith(line) != null) {
                points.add(this.getRight().intersectionWith(line));
            }
        }
        return points;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the top line of the rectangle.
     *
     * @return the top line of the rectangle
     */
    public Line getTop() {
        return new Line(this.getUpperLeft(), new Point(this.getWidth() + this.getUpperLeft().getX(),
                this.getUpperLeft().getY()));
    }

    /**
     * Returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle
     */
    public Line getBottom() {
        return new Line(new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight()),
                new Point(this.getUpperLeft().getX(), this.getHeight() + this.getUpperLeft().getY()));
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLeft() {
        return new Line(this.getUpperLeft(), new Point(this.getUpperLeft().getX(),
                this.getHeight() + this.getUpperLeft().getY()));
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getRight() {
        return new Line(new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()),
                new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight()));
    }
}
