package SpritesAndCollidable;

import Geometry.Point;
import Geometry.Rectangle;

/**
 * An interface with the characteristics of a SpritesAndCollidable.Collidable object.
 *
 * @author ori katzir
 * @version ass5
 * @since 2022/05/22
 */
public interface Collidable {

    /**
     * a method made to get the SpritesAndCollidable.Collidable object's Geometry.Rectangle member.
     *
     * @return the object's Geometry.Rectangle member
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object we collided with at collisionPoint with given velocity.
     *
     * @param collisionPoint  the point which the collision occurred
     * @param currentVelocity the given velocity of the collided object
     * @param hitter          the Sprites.Ball that hit the collided object
     * @return the new velocity expected after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}