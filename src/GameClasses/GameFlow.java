package GameClasses;

import GameClasses.Animations.AnimationRunner;
import GameClasses.Animations.EndGameAnimation;
import GameClasses.Animations.KeyPressStoppableAnimation;
import Levels.LevelFourPackege.LevelFour;
import Levels.LevelInformation;
import Levels.LevelOnePackage.LevelOne;
import Levels.LevelThreePackege.LevelThree;
import Levels.LevelTwoPackege.LevelTwo;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * this class in charge of running the different levels in this Arknoid game.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class GameFlow {

    private List<LevelInformation> levels;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private static final int DEFAULT_FRAMES_PER_SECOND = 60;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final String FIRST_LEVEL = "1";
    public static final String SECOND_LEVEL = "2";
    public static final String THIRD_LEVEL = "3";
    public static final String FORTH_LEVEL = "4";

    /**
     * A constructor method.
     */
    public GameFlow() {
        this.gui = new GUI("Arkanoid", FRAME_WIDTH, FRAME_HEIGHT);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.animationRunner = new AnimationRunner(this.gui, DEFAULT_FRAMES_PER_SECOND);
        this.score = new Counter();
    }

    /**
     * Creates a list of LevelInformation that make playable levels in this Arknoid game.
     * <p>The items in the list are set by the args string array given in the command line</p>
     * <p>The default list of level is levels 1 to 4</p>
     *
     * @param args strings that represent the desired levels to be played
     */
    public void creatLevelList(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        } else {
            for (String arg : args) {
                if (arg.equals(FIRST_LEVEL)) {
                    levels.add(new LevelOne());
                } else if (arg.equals(SECOND_LEVEL)) {
                    levels.add(new LevelTwo());
                } else if (arg.equals(THIRD_LEVEL)) {
                    levels.add(new LevelThree());
                } else if (arg.equals(FORTH_LEVEL)) {
                    levels.add(new LevelFour());
                }
            }
        }
        this.levels = levels;
    }

    /**
     * a method that runs all the levels in this Arknoid game.
     */
    public void runLevels() {
        boolean didPlayerWon = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level =
                    new GameLevel(this.gui, this.score, this.keyboardSensor,
                            this.animationRunner);
            level.initialize(levelInfo);
            while (level.getBallsInGame().getValue() > 0
                    && level.getBlocksInGame().getValue() > 0) {
                level.run();
            }
            if (level.getBallsInGame().getValue() == 0) {
                didPlayerWon = false;
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(new EndGameAnimation(didPlayerWon,
                this.score), this.keyboardSensor, this.keyboardSensor.SPACE_KEY));
        this.gui.close();
    }
}