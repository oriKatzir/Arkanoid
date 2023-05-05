package Listeners;

import SpritesAndCollidable.Ball;
import SpritesAndCollidable.Block;

/**
 * An interface with the characteristics of a Listeners.HitListener object.
 * <p>this class is used for implementing the Observer pattern</p>
 *
 * @author ori katzir
 * @version ass5
 * @since 2022/05/22
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that was hit
     * @param hitter   the Sprites.Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}