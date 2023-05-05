package Listeners;

import GameClasses.Counter;
import GameClasses.GameLevel;
import SpritesAndCollidable.Ball;
import SpritesAndCollidable.Block;

/**
 * a class that get informed when a block was hit and removes it from it.
 * <p>this class is used for implementing the Observer pattern</p>
 *
 * @author ori katzir
 * @version ass5
 * @see HitListener
 * @since 2022/05/22
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * a constructor of a Listeners.BlockRemover listener object.
     *
     * @param game          the game that this listener effects
     * @param removedBlocks a GameClasses.Counter that tracks the blocks in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * the effect of this listener, removes the block that wse hit and decrease the counter.
     * <p>after this method is activated this listener is removed from the block</p>
     *
     * @param beingHit the object that was hit
     * @param hitter   the Sprites.Ball that's doing the hitting
     * @see HitListener#hitEvent(Block, Ball)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}