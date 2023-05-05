package GameClasses.GameLevelBuilders;

import GameClasses.GameLevel;
import Geometry.Point;
import SpritesAndCollidable.Paddle;

/**
 * A paddle builder, used to build and set the paddle of the game level.
 * <p>this class used for implementing the Builder pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class PaddleBuilder {

    private int paddleWidth;
    private int paddleSpeed;
    private Point startingUpperLeftP;
    private GameLevel gameLevel;
    private static final int DEFAULT_PADDLE_SIZE = 200;
    private static final double DEFAULT_STARTING_X_AXIS_VALUE = 400;
    private static final double MIDDLE_OF_X_AXIS = 400;
    private static final double DEFAULT_HEIGHT = 15;

    /**
     * A constructor method.
     *
     * @param paddleSpeed the speed of the desired paddle
     * @param paddleWidth the width of the desired paddle
     * @param gameLevel   the current game level
     */
    public PaddleBuilder(int paddleSpeed, int paddleWidth, GameLevel gameLevel) {
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.gameLevel = gameLevel;
        makeSureThePaddleIsLegit();
    }

    /**
     * this method makes sure the width that was given is legit.
     * <p>if not a legit width will be  supplied</p>
     */
    private void makeSureThePaddleIsLegit() {
        int maxPaddleWidth = GameLimitsBuilder.FRAME_WIDTH - (GameLimitsBuilder.BORDER_WIDTH * 2);
        if (this.paddleWidth >= maxPaddleWidth) {
            this.paddleWidth = maxPaddleWidth - GameLimitsBuilder.BORDER_WIDTH;
        }
        if (this.paddleWidth <= 0) {
            this.paddleWidth = DEFAULT_PADDLE_SIZE;
        }
    }

    /**
     * finds the starting point  that the paddle wil spawn on.
     */
    private void findStartingPoint() {
        double xAxisStartingValue = MIDDLE_OF_X_AXIS - ((double) this.paddleWidth / 2);
        double yAxisStartingValue = Paddle.STATIC_Y_VALUE_OF_UPPER_SIDE;
        this.startingUpperLeftP = new Point(xAxisStartingValue, yAxisStartingValue);
    }

    /**
     * thei method creat the desired paddle.
     *
     * @return the desired paddle
     */
    public Paddle build() {
        findStartingPoint();
        Paddle newPaddle = new Paddle(gameLevel.getKeyboardSensor(),
                GameLimitsBuilder.FRAME_WIDTH,
                this.startingUpperLeftP, this.paddleWidth, DEFAULT_HEIGHT);
        newPaddle.setSpeed(this.paddleSpeed);
        return newPaddle;
    }
}
