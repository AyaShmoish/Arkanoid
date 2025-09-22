// 212831325 Aya Shmoish
package geometry;

/**
 * Geometry.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a new Geometry.Velocity object with the given dx and dy values.
     *
     * @param dx The change in the x direction
     * @param dy The change in the y direction
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Applies the velocity to a given point and returns a new point with the updated position.
     *
     * @param p The point to which the velocity is applied
     * @return A new point with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Gets the change in the x direction.
     *
     * @return The change in the x direction
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets the change in the y direction.
     *
     * @return The change in the y direction
     */
    public double getDy() {
        return dy;
    }
    /**
     * Sets the vertical component of the velocity.
     *
     * @param dy the new vertical component of the velocity.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Sets the horizontal component of the velocity.
     *
     * @param dx the new horizontal component of the velocity.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * Converts an angle and speed to dx and dy values and creates a new Geometry.Velocity object.
     *
     * @param angle The angle in degrees
     * @param speed The speed
     * @return A new Geometry.Velocity object with calculated dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.cos(radians);
        double dy = speed * Math.sin(radians);
        return new Velocity(dx, dy);
    }
}
