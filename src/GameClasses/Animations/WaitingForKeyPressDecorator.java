package GameClasses.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class gives Animation implementing  object the characteristic of waiting a key animation.
 * <p>this method used in the implementation of the decorator pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see Animation
 * @since 2022/06/02
 */
public abstract class WaitingForKeyPressDecorator implements Animation {

    private KeyboardSensor keyboardSensor;
    private boolean shouldStop;
    private Animation waitingForKeyPressAnimation;

    /**
     * A constructor method.
     *
     * @param keyboardSensor              the keyboard that is used in the game
     * @param waitingForKeyPressAnimation an animation implementing object that will be decorated
     */
    public WaitingForKeyPressDecorator(KeyboardSensor keyboardSensor, Animation waitingForKeyPressAnimation) {
        this.waitingForKeyPressAnimation = waitingForKeyPressAnimation;
        this.keyboardSensor = keyboardSensor;
        this.shouldStop = false;
    }

    /**
     * A setter for the shouldStop member.
     *
     * @param shouldStop holds the info that tells if this animation should be stopped
     */
    protected void setStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    /**
     * A getter for the keyboardSensor member.
     *
     * @return the keyboardSensor member
     */
    protected KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }

    /**
     * this method in charge of what the animation will do in every frame.
     *
     * @param d the given DrawSurface the animation wil be display on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        waitingForKeyPressAnimation.doOneFrame(d);
    }

    /**
     * a method finds out if this animation should stop or not.
     *
     * @return the answer for should this animation should be stopped
     */
    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
}
