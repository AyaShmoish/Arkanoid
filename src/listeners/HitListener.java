// 212831325 Aya Shmoish
package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * A HitListener is notified whenever a hit event occurs.
 */
public interface HitListener {
    /**
     * The method is being called whenever the beingHit object is hit.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
