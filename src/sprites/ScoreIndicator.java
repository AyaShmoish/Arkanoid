// 212831325 Aya Shmoish
package sprites;

import biuoop.DrawSurface;
import counter.Counter;

/**
 * A ScoreIndicator displays the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Constructs a ScoreIndicator.
     *
     * @param score the counter for the current score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    @Override
    public void timePassed() {
        //
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(390, 15, "Score: " + score.getValue(), 15);
    }
}
