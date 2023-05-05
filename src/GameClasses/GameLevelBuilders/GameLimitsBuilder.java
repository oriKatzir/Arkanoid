package GameClasses.GameLevelBuilders;

import GameClasses.GameEnvironment;
import GameClasses.GameLevel;
import Listeners.BallRemover;
import SpritesAndCollidable.Block;

import java.awt.Color;

/**
 * A limits builder, used to build and set the boarders of the game frame.
 * <p>this class used for implementing the Builder pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class GameLimitsBuilder {
    private GameLevel gameLevel;
    private Block rightBorder;
    private Block leftBorder;
    private Block upperBorder;
    private Block downBorder;
    private static final int FRAME_START = 0;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int BORDER_WIDTH = 20;
    private static final int BORDER_HEIGHT = 700;
    private static final int BORDER_HORIZONTAL_W = 740;
    private static final int BORDER_HORIZONTAL_H = 40;
    private static final int SAFETY_BUFFER = 400;

    /**
     * A constructor method.
     *
     * @param gameLevel the current level that is playing
     */
    public GameLimitsBuilder(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        setBorders();
        setTheDownLimitToKillerBlock();
        setGameLevelEnvironment();
        addBordersToGameLevelObstaclesAndSprites();
        setGameLevelBordersMembers();
    }

    /**
     * sets the game environment of the game level by setting its limits.
     */
    private void setGameLevelEnvironment() {
        double rightLimit = rightBorder.getCollisionRectangle().getUpperLeft().getX();
        double leftLimit = leftBorder.getCollisionRectangle().getUpperLeft().getX()
                + leftBorder.getCollisionRectangle().getWidth();
        double upLimit = upperBorder.getCollisionRectangle().getUpperLeft().getY()
                - upperBorder.getCollisionRectangle().getHeight();
        double downLimit = downBorder.getCollisionRectangle().getUpperLeft().getY();
        this.gameLevel.setEnvironment(new GameEnvironment(leftLimit, rightLimit, upLimit, downLimit));
    }

    /**
     * sets the members of the GameLevel with the built borders.
     */
    private void setGameLevelBordersMembers() {
        this.gameLevel.setDownBorder(this.downBorder);
        this.gameLevel.setLeftBorder(this.leftBorder);
        this.gameLevel.setRightBorder(this.rightBorder);
        this.gameLevel.setUpperBorder(this.upperBorder);
    }

    /**
     * sets the blocks that confine the objects in this game in the frame.
     * <p>the frame of this game is sets to size of 800X600</p>
     */
    private void setBorders() {
        int rightBorderX = FRAME_WIDTH - BORDER_WIDTH;
        int rightBorderY = FRAME_HEIGHT + SAFETY_BUFFER;
        int leftBorderX = FRAME_START;
        int leftBorderY = FRAME_HEIGHT + SAFETY_BUFFER;
        int upperBorderX = FRAME_START;
        int upperBorderY = BORDER_HEIGHT;
        int downBorderX = FRAME_START;
        int downBorderY = BORDER_WIDTH + BORDER_WIDTH;

        this.rightBorder =
                new Block(rightBorderX, rightBorderY,
                        BORDER_WIDTH + SAFETY_BUFFER,
                        BORDER_HEIGHT + SAFETY_BUFFER, Color.GRAY);
        this.leftBorder =
                new Block(leftBorderX - SAFETY_BUFFER, leftBorderY,
                        BORDER_WIDTH + SAFETY_BUFFER,
                        BORDER_HEIGHT + SAFETY_BUFFER, Color.GRAY);
        this.upperBorder =
                new Block(upperBorderX, upperBorderY,
                        FRAME_WIDTH + SAFETY_BUFFER, BORDER_HORIZONTAL_H,
                        Color.GRAY);
        this.downBorder =
                new Block(downBorderX - SAFETY_BUFFER, downBorderY,
                        FRAME_WIDTH + SAFETY_BUFFER + SAFETY_BUFFER,
                        BORDER_HORIZONTAL_H + SAFETY_BUFFER, Color.GRAY);
    }

    /**
     * sets the lower limit as a killer Block.
     *
     * @see BallRemover
     */
    private void setTheDownLimitToKillerBlock() {
        BallRemover ballRemoverListener = new BallRemover(this.gameLevel, this.gameLevel.getBallsInGame());
        this.upperBorder.addHitListener(ballRemoverListener);
    }

    /**
     * adds the borders of this game to its obstacles and Sprites lists.
     * <p>the obstacles list is a member of this game GameClasses.GameEnvironment</p>
     */
    private void addBordersToGameLevelObstaclesAndSprites() {
        this.gameLevel.addSprite(this.rightBorder);
        this.gameLevel.addSprite(this.leftBorder);
        this.gameLevel.addSprite(this.downBorder);
        this.gameLevel.addSprite(this.upperBorder);

        this.gameLevel.addCollidable(this.rightBorder);
        this.gameLevel.addCollidable(this.leftBorder);
        this.gameLevel.addCollidable(this.downBorder);
        this.gameLevel.addCollidable(this.upperBorder);
    }
}
