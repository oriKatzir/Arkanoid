package GameClasses.Animations;

import GameClasses.Counter;
import biuoop.DrawSurface;

/**
 * this class is the win or lose screen animation.
 * <p>if the player won a win screen will be display if not then a lose screen</p>
 * <p>this method used in the implementation of the decorator pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see Animation
 * @since 2022/06/02
 */
public class EndGameAnimation implements Animation {

    private boolean didPlayerWon;
    private Counter score;
    private String message;
    private static final String WINNING_MESSAGE = "You Win! Your score is ";
    private static final String LOSING_MESSAGE = "Game Over. Your score is ";

    /**
     * A constructor method.
     *
     * @param didPlayerWon holds true if the player won the game or false if not
     * @param score        a Counter object that holds the score of the player
     * @see Counter
     */
    public EndGameAnimation(boolean didPlayerWon, Counter score) {
        this.didPlayerWon = didPlayerWon;
        this.score = score;
        setMessage();
    }

    /**
     * A method that sets the message that will be displayed in this animation.
     * <p>this is done according the info that the didPlayerWon member</p>
     */
    private void setMessage() {
        if (didPlayerWon) {
            this.message = WINNING_MESSAGE;
        } else {
            this.message = LOSING_MESSAGE;
        }
        this.message += this.score.getValue();
    }

    /**
     * this method in charge of what the animation will do in every frame.
     *
     * @param d the given DrawSurface the animation wil be display on
     * @see Animation#doOneFrame(biuoop.DrawSurface)
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, this.message, 32);
    }

    /**
     * a method finds out if this animation should stop or not.
     *
     * @return the answer for should this animation should be stopped
     * @see Animation#shouldStop()
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
