// 212831325 Aya Shmoish
package collision;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * Represents objects that can be collided with.
 */
public interface Collidable {
    /**
     * Returns the collision shape of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at collisionPoint with a given velocity.
     * Returns the new velocity expected after the hit.
     *
     * @param collisionPoint the point of collision
     * @param currentVelocity the current velocity
     * @param hitter the hitter ball
     * @return the new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
