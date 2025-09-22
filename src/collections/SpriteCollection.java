// 212831325 Aya Shmoish
package collections;
import sprites.Sprite;
import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;

/**
 * The Collections.SpriteCollection class holds a collection of Sprites.Sprite objects.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Constructs a Collections.SpriteCollection object.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Removes a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * Calls drawOn(d) on all sprites.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
