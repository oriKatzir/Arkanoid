import GameClasses.GameFlow;

/**
 * the required class that's run the game.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class RunGame {

    /**
     * the main method.
     *
     * @param args sets the level this game will have
     */
    public static void main(String[] args) {
        GameFlow gameFlow = new GameFlow();
        gameFlow.creatLevelList(args);
        gameFlow.runLevels();


    }
}
