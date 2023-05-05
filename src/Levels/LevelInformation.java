package Levels;

import SpritesAndCollidable.Block;
import SpritesAndCollidable.Sprite;
import SpritesAndCollidable.Velocity;

import java.util.List;

/**
 * An interface with the characteristics of a level in an Arknoid game.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/05/30
 */
public interface LevelInformation {

    /**
     * a method that gets the number of bale in this level.
     *
     * @return the number of Balls in this level
     * @see LevelInformation#numberOfBalls()
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball in the level.
     * <p>the size of the velocity List in the number of Balls in the level</p>
     *
     * @return a List of the initial velocity of each ball in the level
     * @see LevelInformation#initialBallVelocities()
     */
    List<Velocity> initialBallVelocities();

    /**
     * a method that returns the speed of the paddle in the level.
     * <p>the speed is the numbers of units the paddle moves on the x axis</p>
     *
     * @return the speed of the paddle
     * @see LevelInformation#paddleSpeed()
     */
    int paddleSpeed();

    /**
     * a method that returns the width of the paddle in this level.
     *
     * @return the width of the paddle
     * @see LevelInformation#paddleWidth()
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return a string that represents the name of the level
     * @see LevelInformation#levelName()
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     * @see LevelInformation#getBackground()
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level.
     * <p>each block contains its size, color and location</p>
     *
     * @return a list of Blocks that make up this level
     * @see LevelInformation#blocks()
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed so this level will be "cleared".
     * <p>This number should be <= blocks.size();</p>
     * @return the number of blocks needed to clear this level
     * @see LevelInformation#numberOfBlocksToRemove()
     */
    int numberOfBlocksToRemove();
}