// 212831325 Aya Shmoish
package sprites;
import biuoop.DrawSurface;

/**
 * Represents a game object that can be drawn to the screen and notified that time has passed.
 */
public interface Sprite {
    /**
     * Draws the sprite to the screen.
     *
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();
}
