package Listeners;

import GameClasses.Counter;
import GameClasses.GameLevel;
import SpritesAndCollidable.Ball;
import SpritesAndCollidable.Block;

import java.awt.Color;

/**
 * An object that get informed when an "extra ball" block was hit and then adds a ball to the game.
 * <p>this class is used for implementing the Observer pattern</p>
 *
 * @author ori katzir
 * @version ass5
 * @see HitListener
 * @since 2022/05/22
 */
public class BallAdder implements HitListener {
    private Counter ballsInGameCounter;
    private GameLevel game;
    private static final int BALL_RADIUS = 10;
    private static final int VELOCITY_VALUE = 3;

    /**
     * a constructor of a Listeners.BallAdder listener object.
     *
     * @param game               the game that this listener effects
     * @param ballsInGameCounter a GameClasses.Counter that tracks the balls in the game
     */
    public BallAdder(GameLevel game, Counter ballsInGameCounter) {
        this.game = game;
        this.ballsInGameCounter = ballsInGameCounter;
    }

    /**
     * creates a new ball and adds it to the game, and increas the ball counter.
     *
     * @param beingHit the object that was hit
     * @param hitter   the Sprites.Ball that's doing the hitting
     * @see HitListener#hitEvent(Block, Ball)
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.ballsInGameCounter.increase(1);
        double newBallCenterXValue =
                beingHit.getCollisionRectangle().getUpperSide().middle().getX();
        double newBallCenterYValue =
                beingHit.getCollisionRectangle().getUpperSide().middle().getY();
        Ball newBall =
                new Ball(newBallCenterXValue, newBallCenterYValue, BALL_RADIUS, Color.GREEN);
        newBall.setVelocity(VELOCITY_VALUE, VELOCITY_VALUE);
        newBall.addToGame(this.game);
    }
}
