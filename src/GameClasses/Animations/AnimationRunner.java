package GameClasses.Animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * an object that runs the animation loop of a given object that implementing the Animation interface.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper = new Sleeper();
    private int framesPerSecond;

    /**
     * A constructor method.
     *
     * @param gui             the GUI object used for this game to run with
     * @param framesPerSecond number of frames that will be displayed every second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * This method runs the Animation by starting the animation loop.
     *
     * @param animation the given object that implements the Animation interface
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}