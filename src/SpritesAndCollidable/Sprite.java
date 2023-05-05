package SpritesAndCollidable;

import GameClasses.GameLevel;

/**
 * An interface with the characteristics of a Sprites.Sprite object.
 *
 * @author ori katzir
 * @version ass5
 * @since 2022/05/22
 */
public interface Sprite {

    /**
     * draw the sprite ton the given DrawSurface.
     *
     * @param d the given DrawSurface
     */
    void drawOn(biuoop.DrawSurface d);

    /**
     * notify the sprite that time has passed, by that effects its animation.
     */
    void timePassed();

    /**
     * Adds the Sprites.Sprite to the given GameClasses.GameLevel object's list of Sprites member.
     *
     * @param game the given GameClasses.GameLevel object
     */
    void addToGame(GameLevel game);
}