package GameClasses.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this method is used to decorate an Animation implementing class.
 * <p>gives animation the characteristic of being stopped when a specific key is pressed</p>
 * <p>this method used in the implementation of the decorator pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see WaitingForKeyPressDecorator
 * @since 2022/06/02
 */
public class KeyPressStoppableAnimation extends WaitingForKeyPressDecorator {
    private boolean isKeyAlreadyPressed;
    private final String key;

    /**
     * A constructor method.
     *
     * @param decoratedAnimation the Animation implementing object to be decorated
     * @param keyboardSensor     the keyboard sensor that is used in the game
     * @param key                holds the name of the key that pressing on will stop the animation
     */
    public KeyPressStoppableAnimation(Animation decoratedAnimation, KeyboardSensor keyboardSensor, String key) {
        super(keyboardSensor, decoratedAnimation);
        this.key = key;
        this.isKeyAlreadyPressed = true;
    }

    /**
     * this method in charge of what the animation will do in every frame.
     *
     * @param d the given DrawSurface the animathion wil be display on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        super.doOneFrame(d);
        if (super.getKeyboardSensor().isPressed(this.key)) {
            if (!this.isKeyAlreadyPressed) {
                super.setStop(true);
            }
        } else {
            this.isKeyAlreadyPressed = false;
        }
    }
}
