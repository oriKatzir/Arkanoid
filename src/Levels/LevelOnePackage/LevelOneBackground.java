package Levels.LevelOnePackage;

import GameClasses.GameLevel;
import SpritesAndCollidable.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * a sprite that make the background of the first level in this Arknoid game.
 *
 * @author ori katzir
 * @version ass6
 * @see Sprite
 * @since 2022/06/02
 */
public class LevelOneBackground implements Sprite {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    /**
     * draw the sprite ton the given DrawSurface.
     *
     * @param d the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        d.setColor(Color.red);
        d.drawCircle(400, 200, 100);
        d.setColor(Color.MAGENTA);
        d.drawCircle(400, 200, 75);
        d.setColor(Color.PINK);
        d.drawCircle(400, 200, 50);

        d.drawLine(270, 200, 370, 200);
        d.drawLine(430, 200, 530, 200);
        d.drawLine(400, 70, 400, 170);
        d.drawLine(400, 230, 400, 330);
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
