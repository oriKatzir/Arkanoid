package GameClasses;

import SpritesAndCollidable.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A Sprit that display the score of the player and the name of the current level.
 *
 * @author ori katzir
 * @version ass6
 * @see Sprite
 * @since 2022/06/02
 */
public class GameLevelInfoBar implements Sprite {

    private static final int FRAME_WIDTH = 800;
    private static final int BORDER_WIDTH = 20;
    private static final int FONT_SIZE = 20;
    private static final int TEXT_HEIGHT = 18;
    private static final int FRAME_START = 0;
    private static final int SCORE_TEXT_X_AXIS_POSITION = 260;
    private static final int NAME_TEXT_X_AXIS_POSITION = 460;
    private String levelNameToDisplay;
    private Counter currenScore;

    /**
     * A constructor for the GameClasses.GameLevelInfoBar.
     *
     * @param currenScore        A Counter that keeps track of the score of the player
     * @param levelNameToDisplay holds the name of the current level
     */
    public GameLevelInfoBar(Counter currenScore, String levelNameToDisplay) {
        this.currenScore = currenScore;
        this.levelNameToDisplay = levelNameToDisplay;
    }

    /**
     * draw the sprite on the given DrawSurface.
     *
     * @param d the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.RED);
        d.fillRectangle(FRAME_START, FRAME_START, FRAME_WIDTH, BORDER_WIDTH);
        String text = "Score: " + this.currenScore.getValue();
        d.setColor(Color.BLACK);
        d.drawText(SCORE_TEXT_X_AXIS_POSITION, TEXT_HEIGHT, text, FONT_SIZE);
        text = "Level Name: " + this.levelNameToDisplay;
        d.drawText(NAME_TEXT_X_AXIS_POSITION, TEXT_HEIGHT, text, FONT_SIZE);
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
