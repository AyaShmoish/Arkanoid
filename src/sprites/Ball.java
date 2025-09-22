// 212831325 Aya Shmoish
package sprites;
import collections.GameEnvironment;
import collision.CollisionInfo;
import game.Game;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;

import java.util.ArrayList;

import geometry.Velocity;
import listeners.HitListener;

import java.util.List;

/**
 * Represents a ball with a specific size, color, and location.
 */
public class Ball implements Sprite {
    private final int r; // The size of the ball
    private java.awt.Color color; // The color of the ball
    private Point center; // The location of the center of the ball
    private Velocity velocity; // The velocity of the ball
    private GameEnvironment g;
    private final List<HitListener> hitListeners;

    /**
     * Constructs a new Sprites.Ball object with the given center point, radius, and color.
     *
     * @param center The center point of the ball
     * @param r      The radius of the ball
     * @param color  The color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0); // Initialize with zero velocity
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Gets the x-coordinate of the center of the ball.
     *
     * @return The x-coordinate of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y-coordinate of the center of the ball.
     *
     * @return The y-coordinate of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the size of the ball.
     *
     * @return The size of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the color of the ball.
     *
     * @return The color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The DrawSurface on which to draw the ball
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * Adds this sprite to the game.
     *
     * @param g the game to add the sprite to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Sets the velocity of the ball using a Geometry.Velocity object.
     *
     * @param v The new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball using dx and dy values.
     *
     * @param dx The change in the x direction
     * @param dy The change in the y direction
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    /**
     * Sets the center of the ball using x and y values.
     *
     * @param x The x value
     * @param y The y value
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return The velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the game environment of the ball.
     *
     * @param g The game environment of the ball.
     */
    public void setEnvironment(GameEnvironment g) {
        this.g = g;
    }

    /**
     * Moves the ball one step according to the velocity.
     */
    public void moveOneStep() {
        //Compute the ball's trajectory
        Point posNext = this.getVelocity().applyToPoint(this.center);
        int posX = velocity.getDx() > 0 ? this.r : -this.r;
        int posY = velocity.getDy() > 0 ? this.r : -this.r;
        posNext = new Point(posNext.getX() + posX, posNext.getY() + posY);
        Line trajectory = new Line(this.center, posNext);

        CollisionInfo collisionInfo = this.g.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            // There is a collision, move the ball to "almost" the hit point
            double moveX = center.getX() < collisionInfo.collisionPoint().getX()
                    ? collisionInfo.collisionPoint().getX() - posX - 1
                    : collisionInfo.collisionPoint().getX() - posX + 1;
            double moveY = center.getY() < collisionInfo.collisionPoint().getY()
                    ? collisionInfo.collisionPoint().getY() - posY - 1
                    : collisionInfo.collisionPoint().getY() - posY + 1;
            this.center = new Point(moveX, moveY);

            // get the new velocity
            this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);

        }

        this.center = this.getVelocity().applyToPoint(this.center);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Removes the ball from the game.
     *
     * @param game the game from which the ball will be removed
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
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