// 212831325 Aya Shmoish
package geometry;
/**
 * Represents a point in a 2D coordinate system.
 */
public class Point {
    private static final double THRESHOLD = 0.00001;
    private final double x;
    private final double y;
    /**
     * Constructs a new point at the specified (x, y) location.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Returns the distance from this point to the specified other point.
     *
     * @param other the other point
     * @return the distance to the other point
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * Compares this point to the specified other point for equality.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.x - other.x) < THRESHOLD && Math.abs(this.y - other.y) < THRESHOLD;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}
