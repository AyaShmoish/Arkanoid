// 212831325 Aya Shmoish
package sprites;
import collision.Collidable;
import game.Game;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Velocity;

import java.awt.Color;

/**
 * Represents a paddle in the game.
 * It implements the Sprites.Sprite and Collision.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int speed;
    private int screenWidth;

    /**
     * The Sprites.Paddle object constructor.
     *
     * @param keyboard the keyboard sensor to control the paddle
     * @param rectangle the rectangle representing the paddle's shape and position
     * @param color the color of the paddle
     * @param speed the speed of the paddle
     * @param screenWidth the width of the screen
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color, int speed, int screenWidth) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
        this.speed = speed;
        this.screenWidth = screenWidth;
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        Point recPoint = rectangle.getUpperLeft();
        double posX = recPoint.getX() - speed;
        // Check if it got to the right side of the screen
        if (posX < 30) {
            posX = screenWidth - 30 - rectangle.getWidth();
        }
        rectangle = new Rectangle(new Point(posX, recPoint.getY()), rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        Point recPoint = rectangle.getUpperLeft();
        double posX = recPoint.getX() + speed;
        // Check if it got to the left side of the screen
        if (posX + rectangle.getWidth() > screenWidth - 30) {
            posX = 30;
        }
        rectangle = new Rectangle(new Point(posX, recPoint.getY()), rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * Updates the paddle's position.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Returns the rectangle representing the paddle's collision shape.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Handles the collision with the paddle and returns the new velocity of the object.
     *
     * @param collisionPoint the point of collision
     * @param currentVelocity the object's current velocity
     * @return the new velocity after the hit
     */
    @Override
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        double region1X = rectangle.getUpperLeft().getX();
        double region2X = region1X + (double) screenWidth / 5;
        double region3X = region2X + (double) screenWidth / 5;
        double region4X = region3X + (double) screenWidth / 5;
        double region5X = region1X + rectangle.getWidth();
        double collisionPointX = collisionPoint.getX();
        double speed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                + currentVelocity.getDy() * currentVelocity.getDy());

        if (collisionPointX >= region1X && collisionPointX < region2X) {
            if (currentVelocity.getDx() > 0) {
                currentVelocity = Velocity.fromAngleAndSpeed(300, speed);
            } else {
                currentVelocity = Velocity.fromAngleAndSpeed(240, speed);
            }
        } else if (collisionPointX >= region2X && collisionPointX < region3X) {
            if (currentVelocity.getDx() > 0) {
                currentVelocity = Velocity.fromAngleAndSpeed(330, speed);
            } else {
                currentVelocity = Velocity.fromAngleAndSpeed(210, speed);
            }
        } else if (collisionPointX >= region3X && collisionPointX < region4X) {
            currentVelocity = Velocity.fromAngleAndSpeed(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (collisionPointX >= region4X && collisionPointX < region5X) {
            if (currentVelocity.getDx() > 0) {
                currentVelocity = Velocity.fromAngleAndSpeed(30, speed);
            } else {
                currentVelocity = Velocity.fromAngleAndSpeed(150, speed);
            }
        } else {
            if (currentVelocity.getDx() > 0) {
                currentVelocity = Velocity.fromAngleAndSpeed(60, Math.sqrt(currentVelocity.getDx()
                        * currentVelocity.getDx() + currentVelocity.getDy() * currentVelocity.getDy()));
            } else {
                currentVelocity = Velocity.fromAngleAndSpeed(120, Math.sqrt(currentVelocity.getDx()
                        * currentVelocity.getDx() + currentVelocity.getDy() * currentVelocity.getDy()));
            }
        }
        return currentVelocity;
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
