// 212831325 Aya Shmoish

import game.Game;

/**
 * contains the main method to start the game.
 */
public class Ass5Game {

    /**
     * The main method to start the game.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
