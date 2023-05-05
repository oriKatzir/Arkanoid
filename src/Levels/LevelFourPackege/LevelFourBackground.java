package Levels.LevelFourPackege;

import GameClasses.GameLevel;
import SpritesAndCollidable.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * a sprite that make the background of the forth level in this Arknoid game.
 *
 * @author ori katzir
 * @version ass6
 * @see Sprite
 * @since 2022/06/02
 */
public class LevelFourBackground implements Sprite {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    /**
     * draw the sprite ton the given DrawSurface.
     *
     * @param d the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue.darker().darker());
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        d.setColor(Color.GRAY.brighter());
        d.fillRectangle(117, 250, 15, 150);
        d.setColor(Color.GRAY);
        d.fillRectangle(100, 375, 50, 75);
        d.setColor(Color.WHITE.darker());
        d.fillRectangle(75, 425, 100, 200);
        d.setColor(Color.orange);
        d.fillCircle(123, 250, 15);
        d.setColor(Color.WHITE);

        d.setColor(Color.WHITE.darker());
        d.fillCircle(250, 440, 35);
        d.fillCircle(200, 420, 25);
        d.fillCircle(640, 485, 27);
        d.setColor(Color.GRAY);
        d.fillCircle(220, 390, 30);
        d.fillCircle(250, 390, 40);
        d.fillCircle(280, 430, 40);
        d.fillCircle(260, 420, 25);


        d.fillCircle(420, 390, 30);
        d.fillCircle(450, 390, 40);
        d.fillCircle(480, 430, 40);
        d.fillCircle(460, 420, 25);

        d.fillCircle(660, 515, 25);
        d.fillCircle(680, 520, 25);
        d.fillCircle(610, 520, 32);

    }

    /**
     * notify the sprite that time has passed, by that effects its animation.
     */
    @Override
    public void timePassed() {
        return;
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
