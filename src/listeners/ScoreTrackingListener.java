// 212831325 Aya Shmoish
package listeners;

import counter.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * A ScoreTrackingListener keeps track of the game score.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener.
     *
     * @param scoreCounter the counter for the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}