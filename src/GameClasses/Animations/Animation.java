package GameClasses.Animations;

import biuoop.DrawSurface;

/**
 * an interface that sets the characteristics of an animation.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public interface Animation {

    /**
     * this method in charge of what the animation will do in every frame.
     *
     * @param d the given DrawSurface the animation wil be display on
     */
    void doOneFrame(DrawSurface d);

    /**
     * a method finds out if this animation should stop or not.
     *
     * @return the answer for should this animation should be stopped
     */
    boolean shouldStop();
}