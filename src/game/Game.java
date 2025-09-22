// 212831325 Aya Shmoish
package game;
import collections.GameEnvironment;
import collision.Collidable;
import counter.Counter;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import collections.SpriteCollection;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
import java.awt.Color;
import biuoop.KeyboardSensor;

/**
 * The Game.Game class represents the Arkanoid game, handling initialization and game loop.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter score;

    /**
     * Constructs a new Game.Game instance.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls =  new Counter();
        this.score = new Counter();
    }

    /**
     * Adds a collidable object to the game environment.
     * @param c The collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the sprite collection.
     * @param s The sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game by creating blocks, balls, and the paddle, and adding them to the game.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        // Add balls and blocks to the game
        Ball ball1 = new Ball(new Point(50, 50), 4, Color.GREEN);
        ball1.addToGame(this);
        ball1.setEnvironment(environment);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(47, 4));
        remainingBalls.increase(1);

        Ball ball2 = new Ball(new Point(200, 200), 4, Color.RED);
        ball2.addToGame(this);
        ball2.setEnvironment(environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(70, 4));
        remainingBalls.increase(1);

        Ball ball3 = new Ball(new Point(100, 150), 4, Color.YELLOW);
        ball3.addToGame(this);
        ball3.setEnvironment(environment);
        ball3.setVelocity(Velocity.fromAngleAndSpeed(50, 4));
        remainingBalls.increase(1);

        // Create and add multiple blocks
        Block block1 = new Block(new Rectangle(new Point(-20, 20), 840, 30), Color.GRAY);
        block1.addToGame(this);
        Block block2 = new Block(new Rectangle(new Point(770, 50), 40, 600), Color.GRAY);
        block2.addToGame(this);
        Block block3 = new Block(new Rectangle(new Point(-20, 50), 50, 600), Color.GRAY);
        block3.addToGame(this);
        Block deathBlock = new Block(new Rectangle(new Point(-20, 612), 840, 1), Color.BLACK);
        deathBlock.addHitListener(ballRemover);
        deathBlock.addToGame(this);
        Block scoreBlock = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.WHITE);
        scoreBlock.addToGame(this);
        Color[] listC = new Color[6];
        listC[0] = Color.GREEN;
        listC[1] = Color.RED;
        listC[2] = Color.YELLOW;
        listC[3] = Color.MAGENTA;
        listC[4] = Color.cyan;
        listC[5] = Color.WHITE;
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 13 - i; j++) {
                Block b = new Block(new Rectangle(new Point(800 - 30 - 50 * j, 100 + 30 * i), 50, 30), listC[i - 1]);
                b.addToGame(this);
                b.addHitListener(blockRemover);
                b.addHitListener(scoreTrackingListener);
                remainingBlocks.increase(1);
            }
        }
    }

    /**
     * Runs the game, starting the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("game", 800, 600);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle paddleR = new Rectangle(new Point(390, 550), 80, 20);

        Paddle paddle = new Paddle(keyboard, paddleR, Color.cyan, 4, 800);
        this.addSprite(paddle);
        this.addCollidable(paddle);
        paddle.addToGame(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        this.addSprite(scoreIndicator);

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            // Draw the background
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
                gui.close();
                return;
            }
            if (remainingBalls.getValue() == 0) {
                gui.close();
                return;
            }
        }
    }
    /**
     * Removes the given collidable from the game environment.
     *
     * @param c the collidable to be removed from the environment
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes the given sprite from the sprite collection.
     *
     * @param s the sprite to be removed from the collection
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

}
