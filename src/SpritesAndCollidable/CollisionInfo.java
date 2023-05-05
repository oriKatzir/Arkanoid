package SpritesAndCollidable;

import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * this class objects have members that hols information about a collision.
 *
 * @author ori katzir
 * @version ass5
 * @since 2022/05/22
 */
public class CollisionInfo {

    private List<Collidable> obstacles;
    private Line trajectory;
    private Collidable collisionObject;
    private Point collisionP;

    /**
     * construct a new collisionInfo, sets the members.
     *
     * @param trajectory the line that the collided object moves on
     * @param obstacles  a list of obstacles that may be on the trajectory
     */
    public CollisionInfo(Line trajectory, List<Collidable> obstacles) {
        if (obstacles.isEmpty()) {
            this.obstacles = null;
            this.collisionP = null;
            this.collisionObject = null;
            this.trajectory = trajectory;
        }
        this.obstacles = obstacles;
        this.trajectory = trajectory;
        findCollisionPoint();
        findCollisionObject();
    }

    /**
     * construct a new collisionInfo, sets the members.
     *
     * @param collisionP      the point in which the collision occurred
     * @param collisionObject the object that the collision point is on
     */
    public CollisionInfo(Point collisionP, Collidable collisionObject) {
        this.collisionP = collisionP;
        this.collisionObject = collisionObject;
    }

    /**
     * sets the collisionP member of this SpritesAndCollidable.CollisionInfo.
     */
    private void findCollisionPoint() {
        List<Point> collisionPointList = new ArrayList<>();
        Point newP = null;
        Point min = null;

        for (Collidable c : obstacles) {
            newP = this.trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (newP != null) {
                if (min == null) {
                    min = newP;
                }
                if (this.trajectory.getStart().distance(newP)
                        < this.trajectory.getStart().distance(min)) {
                    min = newP;
                }
                collisionPointList.add(newP);
            }
        }
        if (collisionPointList.isEmpty()) {
            this.collisionP = null;
        } else {
            this.collisionP = this.trajectory.findClosestPointToStart(collisionPointList);
        }
    }

    /**
     * sets the collisionObject member of this SpritesAndCollidable.CollisionInfo.
     */
    private void findCollisionObject() {
        if (this.collisionP == null) {
            this.collisionObject = null;
            return;
        }

        for (Collidable c : obstacles) {
            if (c.getCollisionRectangle().isPointOnThisRect(this.collisionP)) {
                this.collisionObject = c;
                break;
            }
        }
    }

    /**
     * getter for this SpritesAndCollidable.CollisionInfo's collisionP member.
     *
     * @return the collision point of this SpritesAndCollidable.CollisionInfo
     */
    public Point collisionPoint() {
        return this.collisionP;
    }

    /**
     * getter for this SpritesAndCollidable.CollisionInfo's collisionObject member.
     *
     * @return the SpritesAndCollidable.Collidable object that the collision occurred on
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}