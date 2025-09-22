// 212831325 Aya Shmoish
package listeners;

import sprites.Ball;
import sprites.Block;
import game.Game;
import counter.Counter;

/**
 * A BlockRemover is responsible for removing blocks from the game,
 * as well as keeping count of the number of blocks that still remain.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructs a BlockRemover.
     *
     * @param game the game from which blocks will be removed
     * @param remainingBlocks the counter for the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    /**
     *  Blocks that are hit should be removed from the game.
     * @param beingHit The block that we want to remove
     * @param hitter The ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            hitter.setColor(beingHit.getColor());
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
            remainingBlocks.decrease(1);
        }
    }
}