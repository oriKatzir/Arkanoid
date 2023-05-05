package GameClasses.Animations;

import SpritesAndCollidable.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * an animation that display a countdown on top of the level screen.
 *
 * @author ori katzir
 * @version ass6
 * @see Animation
 * @since 2022/06/02
 */
public class CountdownAnimation implements Animation {

    private SpriteCollection gameScreen;
    private double numOfSeconds;
    private int countFrom;
    private boolean shouldStop;
    private static final int NUM_OF_MILLISECONDS_IN_A_SECOND = 1000;
    private static final int X_AXIS_POSITION = 400;
    private static final int Y_AXIS_POSITION = 350;
    private static final int FONT_SIZE = 50;

    /**
     * A constructor method.
     *
     * @param numOfSeconds the num of seconds every digit will be displayed
     * @param countFrom    the number this countdown will start from
     * @param gameScreen   the screen of the level this animation is played on top of
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = (numOfSeconds * NUM_OF_MILLISECONDS_IN_A_SECOND) / countFrom;
        this.countFrom = Math.max(0, countFrom);
        this.gameScreen = gameScreen;
        this.shouldStop = false;
    }

    /**
     * this method in charge of what the animation will do in every frame.
     *
     * @param d the given DrawSurface the animation wil be display on
     */
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.CYAN);
        if (this.countFrom < -1) {
            this.shouldStop = true;
            return;
        }
        if (this.countFrom == 0) {
            d.drawText(X_AXIS_POSITION, Y_AXIS_POSITION, "GO!", FONT_SIZE);

        } else {
            d.drawText(X_AXIS_POSITION, Y_AXIS_POSITION, String.valueOf(this.countFrom), FONT_SIZE);
        }
        this.countFrom--;
        sleeper.sleepFor((long) this.numOfSeconds);
    }

    /**
     * a method finds out if this animation should stop or not.
     *
     * @return the answer for should this animation should be stopped
     */
    public boolean shouldStop() {
        return this.shouldStop;
    }
}
