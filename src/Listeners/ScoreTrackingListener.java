package Listeners;

import GameClasses.Counter;
import SpritesAndCollidable.Ball;
import SpritesAndCollidable.Block;

/**
 * A class that gets informed by Sprites.Block objects that they were hit and increase the GameClasses.GameLevel's score accordingly.
 * <p>this class is used for implementing the Observer pattern</p>
 *
 * @author ori katzir
 * @version ass5
 * @see HitListener
 * @since 2022/05/22
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private static final int HITTING_A_BLOCK_REWARD_POINTS = 5;

    /**
     * a Listeners.ScoreTrackingListener constructor.
     *
     * @param scoreCounter a GameClasses.Counter that keeps track on the score of the player
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * the events that occures every time a Listeners.HitNotifier get hits.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     * @see HitListener#hitEvent(Block, Ball)
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(HITTING_A_BLOCK_REWARD_POINTS);
        beingHit.removeHitListener(this);
    }
}