package Levels.LevelThreePackege;

import GameClasses.GameLevelBuilders.BlockBuilder;
import Levels.LevelInformation;
import SpritesAndCollidable.Block;
import SpritesAndCollidable.Sprite;
import SpritesAndCollidable.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * the relevant information needed to creat the third level of this game.
 * <p>this class uses strategy classes as part of the Strategy patter</p>
 *
 * @author ori katzir
 * @version ass6
 * @see Levels.LevelInformation
 * @since 2022/06/02
 */
public class LevelThree implements LevelInformation {

    static final String LEVEL_NAME = "Green 3";
    static final int NUM_OF_BALLS = 2;
    static final int INITIAL_BLOCK_NUM = 40;
    static final int PADDLE_SPEED = 15;
    static final int PADDLE_WIDTH = 400;
    private static final int FIRST_BALL_ANGLE = 330;
    private static final int SECOND_BALL_ANGLE = 30;
    private static final int DEFAULT_SPEED = 5;
    static final LevelThreeBlocksPlacementStrategy BLOCK_PLACEMENT =
            new LevelThreeBlocksPlacementStrategy();
    static final LevelThreeBlocksColorStrategy BLOCKS_COLORS =
            new LevelThreeBlocksColorStrategy();

    /**
     * a method that gets the number of bale in this level.
     *
     * @return the number of Balls in this level
     * @see LevelInformation#numberOfBalls()
     */
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     * The initial velocity of each ball in the level.
     * <p>the size of the velocity List in the number of Balls in the level</p>
     *
     * @return a List of the initial velocity of each ball in the level
     * @see LevelInformation#initialBallVelocities()
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(FIRST_BALL_ANGLE, DEFAULT_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(SECOND_BALL_ANGLE, DEFAULT_SPEED));
        return velocities;
    }

    /**
     * a method that returns the speed of the paddle in the level.
     * <p>the speed is the numbers of units the paddle moves on the x axis</p>
     *
     * @return the speed of the paddle
     * @see LevelInformation#paddleSpeed()
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * a method that returns the width of the paddle in this level.
     *
     * @return the width of the paddle
     * @see LevelInformation#paddleWidth()
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return a string that represents the name of the level
     * @see LevelInformation#levelName()
     */
    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     * @see LevelInformation#getBackground()
     */
    @Override
    public Sprite getBackground() {
        return new LevelThreeBackground();
    }

    /**
     * The Blocks that make up this level.
     * <p>each block contains its size, color and location</p>
     *
     * @return a list of Blocks that make up this level
     * @see LevelInformation#blocks()
     */
    @Override
    public List<Block> blocks() {
        BlockBuilder blockBuilder =
                new BlockBuilder(INITIAL_BLOCK_NUM, BLOCK_PLACEMENT, BLOCKS_COLORS);
        return blockBuilder.build();
    }

    /**
     * Number of blocks that should be removed so this level will be "cleared".
     * <p>This number should be <= blocks.size();</p>
     *
     * @return the number of blocks needed to clear this level
     * @see LevelInformation#numberOfBlocksToRemove()
     */
    @Override
    public int numberOfBlocksToRemove() {
        return INITIAL_BLOCK_NUM;
    }
}
