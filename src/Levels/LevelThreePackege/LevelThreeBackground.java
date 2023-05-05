package Levels.LevelThreePackege;

import GameClasses.GameLevel;
import SpritesAndCollidable.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * a sprite that make the background of the third level in this Arknoid game.
 *
 * @author ori katzir
 * @version ass6
 * @see Sprite
 * @since 2022/06/02
 */
public class LevelThreeBackground implements Sprite {

    /**
     * draw the sprite ton the given DrawSurface.
     *
     * @param d the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN.darker());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(153, 153, 153));
        d.fillRectangle(117, 250, 15, 150);
        d.setColor(new Color(102, 102, 102));
        d.fillRectangle(100, 375, 50, 75);
        d.setColor(new Color(51, 51, 51));
        d.fillRectangle(75, 425, 100, 200);
        d.setColor(new Color(204, 204, 204));
        d.setColor(Color.orange);
        d.fillCircle(123, 250, 15);
    }

    /**
     * notify the sprite that time has passed, by that effects its animation.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the Sprites.Sprite to the given GameClasses.GameLevel object's list of Sprites member.
     *
     * @param game the given GameClasses.GameLevel object
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
