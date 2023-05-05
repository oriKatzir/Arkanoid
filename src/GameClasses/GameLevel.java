package GameClasses;

import GameClasses.Animations.Animation;
import GameClasses.Animations.AnimationRunner;
import GameClasses.Animations.KeyPressStoppableAnimation;
import GameClasses.Animations.CountdownAnimation;
import GameClasses.Animations.PauseScreen;
import GameClasses.GameLevelBuilders.BallBuilder;
import GameClasses.GameLevelBuilders.GameLimitsBuilder;
import GameClasses.GameLevelBuilders.PaddleBuilder;
import Levels.LevelInformation;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import SpritesAndCollidable.Ball;
import SpritesAndCollidable.Block;
import SpritesAndCollidable.Collidable;
import SpritesAndCollidable.SpriteCollection;
import SpritesAndCollidable.Sprite;
import SpritesAndCollidable.Paddle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * A class of a playable level in the Arknoid game.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Block upperBorder;
    private biuoop.GUI gui;
    private biuoop.KeyboardSensor keyboardSensor;
    private Counter blocksInGame;
    private Counter ballsInGame;
    private Counter score;
    private static final int CLEARING_ALL_OF_THE_BLOCKS_REWARD_POINTS = 100;
    private static final int NUM_OF_SECOND_TO_COUNTDOWN_FOR = 2;
    private static final int NUM_OF_SECOND_TO_START_COUNTDOWN_FROM = 3;
    private AnimationRunner runner;
    private boolean running;
    private GameLimitsBuilder gameLimits;
    private Paddle paddle;
    private BallBuilder ballBuilder;
    private PaddleBuilder paddleBuilder;
    private Block rightBorder;
    private Block leftBorder;
    private Block downBorder;

    /**
     * A constructor method.
     *
     * @param gui             the Gui object used in the game
     * @param score           A counter that tracks the score of the player throughout the levels
     * @param keyboardSensor  the keyboard sensor that used in the game
     * @param animationRunner an animation runner used to run this GameLevel
     */
    public GameLevel(GUI gui, Counter score, KeyboardSensor keyboardSensor, AnimationRunner animationRunner) {
        this.sprites = new SpriteCollection();
        this.blocksInGame = new Counter();
        this.ballsInGame = new Counter();
        this.score = score;
        this.gui = gui;
        this.keyboardSensor = keyboardSensor;
        this.runner = animationRunner;
    }

    /**
     * A getter for this GameLevel blocksInGame member.
     *
     * @return this GameLevel blocksInGame member
     */
    public Counter getBlocksInGame() {
        return this.blocksInGame;
    }

    /**
     * A getter for this GameLevel ballsInGame member.
     *
     * @return this GameLevel ballsInGame member
     */
    public Counter getBallsInGame() {
        return this.ballsInGame;
    }

    /**
     * A getter for this GameLevel keyboardSensor member.
     *
     * @return this GameLevel keyboardSensor member
     */
    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }

    /**
     * returns this GameLevel environment member.
     *
     * @return gameEnvironment of this game
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * returns this GameLevel sprites member.
     *
     * @return the sprites member of this GameLevel
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * sets this game level with a playable level.
     * <p>this method uses builder as part of the Builder pattern</p>
     *
     * @param levelInformation holds the information of a level in Arknoid game
     */
    public void initialize(LevelInformation levelInformation) {
        levelInformation.getBackground().addToGame(this);
        this.gameLimits = new GameLimitsBuilder(this);
        this.paddleBuilder =
                new PaddleBuilder(levelInformation.paddleSpeed(),
                        levelInformation.paddleWidth(), this);
        this.paddle = paddleBuilder.build();
        this.paddle.addToGame(this);
        this.environment.setPaddle(paddle);
        this.ballBuilder =
                new BallBuilder(levelInformation.numberOfBalls(),
                        levelInformation.initialBallVelocities(),
                        this.paddle.getCenterPointOfDownSide());
        addBlocksToGameLevel(levelInformation.blocks());
        addBallsToGameLevel(ballBuilder.build());
        setCounters(levelInformation);
        creatAndAddGameLevelInfoBar(levelInformation.levelName());
    }

    /**
     * This method creates and add game information bar to the game.
     *
     * @param levelNameToDisplay holds the name of the current level
     */
    private void creatAndAddGameLevelInfoBar(String levelNameToDisplay) {
        GameLevelInfoBar gameLevelInfoBar =
                new GameLevelInfoBar(this.score, levelNameToDisplay);
        gameLevelInfoBar.addToGame(this);
    }

    /**
     * reset and set the appropriate information in the Counters used in this level.
     *
     * @param levelInformation holds the information of a level in Arknoid game
     */
    private void setCounters(LevelInformation levelInformation) {
        this.ballsInGame.reset();
        this.blocksInGame.reset();
        this.ballsInGame.increase(levelInformation.numberOfBalls());
        this.blocksInGame.increase(levelInformation.numberOfBlocksToRemove());
    }

    /**
     * attached listeners to every block in the list and adds them to the level.
     * <p>this method is used as part of the Observer pattern</p>
     *
     * @param blocks a list ob Blocks to add to this game level
     */
    private void addBlocksToGameLevel(List<Block> blocks) {
        BlockRemover removerListener = new BlockRemover(this, this.blocksInGame);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (Block block : blocks) {
            block.addHitListener(removerListener);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
        }
    }

    /**
     * adds the Balls in the list to this game level.
     *
     * @param balls a list of Ball to add to this level
     */
    private void addBallsToGameLevel(List<Ball> balls) {
        for (Ball ball : balls) {
            ball.addToGame(this);
        }
    }

    /**
     * a setter for this gameLevel's GameEnvironment member.
     *
     * @param gameEnvironment this gameLevel's GameEnvironment member
     */
    public void setEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * A setter for this GameLevel rightBorder member.
     *
     * @param rightBorder his GameLevel rightBorder member
     */
    public void setRightBorder(Block rightBorder) {
        this.rightBorder = rightBorder;
    }

    /**
     * A setter for this GameLevel leftBorder member.
     *
     * @param leftBorder this GameLevel leftBorder member
     */
    public void setLeftBorder(Block leftBorder) {
        this.leftBorder = leftBorder;
    }

    /**
     * A setter for this GameLevel upperBorder member.
     *
     * @param upperBorder this GameLevel upperBorder member
     */
    public void setUpperBorder(Block upperBorder) {
        this.upperBorder = upperBorder;
    }

    /**
     * A setter for this GameLevel downBorder member.
     *
     * @param downBorder this GameLevel downBorder member
     */
    public void setDownBorder(Block downBorder) {
        this.downBorder = downBorder;
    }

    /**
     * Adds a Collidable to the list of obstacles of this GameLevel's GameEnvironment member.
     *
     * @param c a SpritesAndCollidable.Collidable object
     * @see Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a Sprites.Sprite to this gameLevel list of Sprites.
     *
     * @param s a Sprites.Sprite object
     * @see Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a Sprites.Sprite to this gameLevel list of Sprites.
     *
     * @param s an Sprites.Sprite object
     * @see Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * this method uses the AnimationRunner member to run this GameLevel.
     */
    public void run() {
        this.running = true;
        runner.run(new CountdownAnimation(NUM_OF_SECOND_TO_COUNTDOWN_FOR,
                NUM_OF_SECOND_TO_START_COUNTDOWN_FROM, this.getSprites()));
        runner.run(this);
    }

    /**
     * this method in charge of what the animation will do in every frame.
     *
     * @param d the given DrawSurface the animation wil be display on
     * @see Animation#doOneFrame(biuoop.DrawSurface)
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.blocksInGame.getValue() == 0) {
            this.score.increase(CLEARING_ALL_OF_THE_BLOCKS_REWARD_POINTS);
            this.running = false;
        }
        if (this.ballsInGame.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(new PauseScreen(),
                    this.keyboardSensor, this.keyboardSensor.SPACE_KEY));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * a method finds out if this animation should stop or not.
     *
     * @return the answer for should this animation should be stopped
     * @see Animation#shouldStop()
     */
    public boolean shouldStop() {
        return !this.running;
    }
}