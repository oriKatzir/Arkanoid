package GameClasses.Animations;

import biuoop.DrawSurface;

/**
 * this is a pause screen animation.
 * <p>this method used in the implementation of the decorator pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see Animation
 * @since 2022/06/02
 */
public class PauseScreen implements Animation {

    /**
     * this method in charge of what the animation will do in every frame.
     *
     * @param d the given DrawSurface the animation wil be display on
     * @see Animation#doOneFrame(biuoop.DrawSurface)
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * a method finds out if this animation should stop or not.
     *
     * @return the answer for should this animation should be stopped
     * @see Animation#shouldStop()
     */
    public boolean shouldStop() {
        return false;
    }
}