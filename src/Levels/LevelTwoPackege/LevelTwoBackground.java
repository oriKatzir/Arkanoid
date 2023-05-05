package Levels.LevelTwoPackege;

import GameClasses.GameLevel;
import SpritesAndCollidable.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * a sprite that make the background of the second level in this Arknoid game.
 *
 * @author ori katzir
 * @version ass6
 * @see Sprite
 * @since 2022/06/02
 */
public class LevelTwoBackground implements Sprite {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    /**
     * draw the sprite ton the given DrawSurface.
     *
     * @param d the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        Color color = Color.YELLOW.brighter();
        d.setColor(color);
        for (int i = 0; i < 100; i++) {
            d.drawLine(200, 200, i * 6, 300);
        }
        for (int i = 0; i < 3; i++) {
            d.setColor(color);
            d.fillCircle(200, 170, 90 - i * 10);
        }
        d.setColor(Color.lightGray);
        d.fillCircle(200, 500, 25);
        d.fillCircle(220, 520, 25);
        d.fillCircle(220, 480, 25);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(248, 480, 25);
        d.fillCircle(258, 520, 25);
        d.fillCircle(275, 500, 25);
        d.setColor(Color.gray.brighter());
        for (int i = 0; i < 10; i++) {
            d.drawLine(200 + i * 10, 500, 70 + i * 10, 700);
        }
        d.setColor(Color.lightGray);
        d.fillCircle(200 + 400, 500, 25);
        d.fillCircle(220 + 400, 520, 25);
        d.fillCircle(220 + 400, 480, 25);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(248 + 400, 480, 25);
        d.fillCircle(258 + 400, 520, 25);
        d.fillCircle(275 + 400, 500, 25);

        d.setColor(Color.gray.brighter());
        for (int i = 0; i < 10; i++) {
            d.drawLine(200 + 400 + i * 10, 500, 200 + i * 10, 900);
        }
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
