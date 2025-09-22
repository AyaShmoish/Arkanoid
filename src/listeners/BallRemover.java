// 212831325 Aya Shmoish
package listeners;

import sprites.Ball;
import sprites.Block;
import game.Game;
import counter.Counter;

/**
 * A BallRemover is responsible for removing balls from the game,
 * and keeping track of the number of balls that still remain.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructs a BallRemover.
     *
     * @param game the game from which balls will be removed
     * @param remainingBalls the counter for the remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    /**
     *  Blocks that are hit should be removed from the game.
     * @param beingHit The block that we want to remove
     * @param hitter The ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            hitter.removeHitListener(this);
            hitter.removeFromGame(game);
            remainingBalls.decrease(1);
    }
}
