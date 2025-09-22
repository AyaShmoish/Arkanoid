// 212831325 Aya Shmoish
package collision;

import geometry.Point;

/**
 * Contains the point at which the collision occurs and the collidable object involved.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructor for Collision.CollisionInfo.
     *
     * @param collisionPoint the point at which the collision occurs.
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
