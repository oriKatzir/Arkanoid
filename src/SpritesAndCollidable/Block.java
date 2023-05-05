package SpritesAndCollidable;

import GameClasses.GameLevel;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Listeners.HitListener;
import Listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Sprites.Block class, an object that have the characteristics of a block.
 *
 * @author ori katzir
 * @version ass5
 * @see Collidable
 * @see Sprite
 * @see HitNotifier
 * @since 2022/05/22
 */
public class Block implements Sprite, Collidable, HitNotifier {

    private Color color;
    private Rectangle rect;
    private List<HitListener> hitListeners = new ArrayList<>();
    private static final int DEFAULT_BLOCK_WIDTH = 50;
    private static final int DEFAULT_BLOCK_HEIGHT = 20;
    private static final Color DEFAULT_COLOR = Color.black;

    /**
     * A constructor, creates a new rectangle member and sets the color.
     *
     * @param upperLeftP the upper left point of this block's rectangle
     * @param width      the width of this block's rectangle
     * @param height     the height of this block's rectangle
     * @param color      the color of this block
     */
    public Block(Point upperLeftP, double width, double height, Color color) {
        this.rect = new Rectangle(upperLeftP, width, height);
        this.color = color;
    }

    /**
     * A constructor, creates a new rectangle member and sets the color.
     *
     * @param x      x-axis value of the upperLeft point of this block's rectangle
     * @param y      y-axis value of the upperLeft point of this block's rectangle
     * @param width  the width of this block's rectangle
     * @param height the height of this block's rectangle
     * @param color  the color of this block
     */
    public Block(double x, double y, double width, double height, Color color) {
        this(new Point(x, y), width, height, color);
    }

    /**
     * constructor that copies a rectangle into this block rectangle.
     *
     * @param rec   the rectangle to be copied
     * @param color the clolor of this block
     */
    public Block(Rectangle rec, Color color) {
        this.rect = new Rectangle(rec);
        this.color = color;
    }

    /**
     * A default constructor method.
     */
    public Block() {
        this(new Point(), DEFAULT_BLOCK_WIDTH, DEFAULT_BLOCK_HEIGHT, DEFAULT_COLOR);
    }


    /**
     * A SpritesAndCollidable.Collidable method.
     *
     * @return this block Geometry.Rectangle member
     * @see Collidable#getCollisionRectangle()
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * A setter for this block color member.
     *
     * @param color a new color for this block
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * this method notify all the listeners of this ball that a hit event has occurred.
     *
     * @param hitter the ball that hit this block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl as a listener to this block hitListeners member.
     *
     * @param hl listener that have an effect when gets informed by this Listeners.HitNotifier
     * @see HitNotifier#addHitListener(HitListener)
     */
    public void addHitListener(HitListener hl) {

        if (hl != null && !this.hitListeners.contains(hl)) {
            this.hitListeners.add(hl);
        }

    }

    /**
     * Remove hl from the list of listeners to hit events of this block.
     *
     * @param hl listener that have an effect when gets informed by this Listeners.HitNotifier
     * @see HitNotifier#removeHitListener(HitListener)
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * A SpritesAndCollidable.Collidable method.
     *
     * @param collisionPoint  the point which the collision occurred
     * @param currentVelocity the given velocity of the collided object
     * @param hitter          the ball that hit this Sprites.Block
     * @return the new velocity expected after the hit
     * @see Collidable#hit(Point, Velocity, Ball)
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        Line leftSide = this.rect.getLeftSide();
        Line rightSide = this.rect.getRightSide();
        Line upperSide = this.rect.getUpperSide();
        Line downSide = this.rect.getDownSide();

        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        if (leftSide.isPointOnThisLine(collisionPoint)
                || rightSide.isPointOnThisLine(collisionPoint)) {
            newDx = -(newDx);
        }
        if (upperSide.isPointOnThisLine(collisionPoint)
                || downSide.isPointOnThisLine(collisionPoint)) {
            newDy = -(newDy);
        }
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }

    /**
     * removes a sprite from the given game level.
     *
     * @param gameLevel the game level that have the going to be deleted sprite
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.getEnvironment().removeCollidable(this);
    }

    /**
     * A Sprites.Sprite method.
     *
     * @param d the given DrawSurface
     * @see Sprite#drawOn(biuoop.DrawSurface)
     */
    public void drawOn(biuoop.DrawSurface d) {
        int width = (int) this.rect.getWidth();
        int height = (int) this.rect.getHeight();
        int upperLeftX = (int) this.rect.getUpperLeft().getX();
        int upperLeftY = (int) this.rect.getUpperLeft().getY();
        d.setColor(this.color);
        d.fillRectangle(upperLeftX, upperLeftY - height, width, height);

        d.setColor(Color.black);
        d.drawRectangle(upperLeftX, upperLeftY - height, width, height);
    }

    /**
     * A Sprites.Sprite method.
     *
     * @see Sprite#timePassed()
     */
    public void timePassed() {

    }

    /**
     * A Sprites.Sprite method.
     *
     * @param game the given GameClasses.GameLevel object
     * @see Sprite#addToGame(GameLevel)
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

}
