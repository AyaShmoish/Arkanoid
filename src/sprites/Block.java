// 212831325 Aya Shmoish
package sprites;
import java.awt.Color;

import collision.Collidable;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;
import game.Game;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

/**
 * Represents a block in the game.
 * It implements the Collision.Collidable and Sprites.Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners;
    private final Rectangle rectangle;
    private final Color color;

    /**
     * Sprites.Block object constructor.
     *
     * @param rectangle the rectangle representing the block's shape and position
     * @param color the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the collision shape of the block.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * Gets the color of the block.
     *
     * @return The color of the block
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * Notifies the block that a collision occurred at collisionPoint with a given velocity.
     * Returns the new velocity expected after the hit.
     *
     * @param collisionPoint the point of collision
     * @param currentVelocity the current velocity
     * @param hitter The ball that hits the block
     * @return the new velocity after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        // Check where the ball hits the block and change velocity accordingly
        if (collisionPoint.getX() > rectangle.getUpperLeft().getX()
                && collisionPoint.getX() < rectangle.getUpperLeft().getX() + rectangle.getWidth()) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }

        if (collisionPoint.getY() > rectangle.getUpperLeft().getY()
                && collisionPoint.getY() < rectangle.getUpperLeft().getY() + rectangle.getHeight()) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }

        return currentVelocity;
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Notifies the block that time has passed.
     */
    @Override
    public void timePassed() {
        // No implementation needed for this example
    }

    /**
     * Adds this block to the game.
     *
     * @param game the game to add the block to
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
    /**
     * Checks if the color of this ball matches the color of another ball.
     *
     * @param ball the ball to compare colors with
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        if (ball.getColor() == this.color) {
            return true;
        }
        return false;
    }

    /**
     * Removes the ball from the specified game.
     *
     * @param game the game from which the ball will be removed
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     *
     * @param hitter the ball that caused the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Adds a hit listener to the ball.
     *
     * @param hl the hit listener to be added
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the ball.
     *
     * @param hl the hit listener to be removed
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

}