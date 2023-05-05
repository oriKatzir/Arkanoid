package Listeners;

import GameClasses.Counter;
import GameClasses.GameLevel;
import SpritesAndCollidable.Ball;
import SpritesAndCollidable.Block;

/**
 * A class that gets informed by DeathBlocks hitInformer when they were hit and remove a ball from the game accordingly.
 * <p>this class is used for implementing the Observer pattern</p>
 *
 * @author ori katzir
 * @version ass5
 * @see HitListener
 * @since 2022/05/22
 */
public class BallRemover implements HitListener {
    private Counter ballsInGameCounter;
    private GameLevel game;

    /**
     * A Listeners.BallRemover listener constructor.
     *
     * @param game               the game that this listener effects
     * @param ballsInGameCounter a GameClasses.Counter that tracks the balls in the game
     */
    public BallRemover(GameLevel game, Counter ballsInGameCounter) {
        this.game = game;
        this.ballsInGameCounter = ballsInGameCounter;
    }

    /**
     * remove the hitter ball frome the game.
     *
     * @param beingHit the object that was hit
     * @param hitter   the Sprites.Ball that's doing the hitting
     * @see HitListener#hitEvent(Block, Ball)
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.ballsInGameCounter.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
