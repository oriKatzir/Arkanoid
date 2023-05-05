package GameClasses;

import Geometry.Line;
import Geometry.Point;
import SpritesAndCollidable.Collidable;
import SpritesAndCollidable.CollisionInfo;
import SpritesAndCollidable.Paddle;

import java.util.ArrayList;
import java.util.List;

/**
 * GameClasses.GameEnvironment class, use to keep track of the obstacles in the game.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class GameEnvironment {

    private List<Collidable> obstacles;
    private Paddle paddle;
    private double leftLimit;
    private double rightLimit;
    private double upLimit;
    private double downLimit;
    /**
     *  * A constructor for game environment.
     * <p>initialize the environment with axis limits that are set in GameClasses.GameLevel</p>
     *
     * @param leftLimit  the left limit of this game environment
     * @param rightLimit the right limit of this game environment
     * @param upLimit    the roof limit of this game environment
     * @param downLimit  the flor limit of this game environment
     */
    public GameEnvironment(double leftLimit, double rightLimit, double upLimit, double downLimit) {
        this.obstacles = new ArrayList<>();
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
        this.upLimit = upLimit;
        this.downLimit = downLimit;
    }

    /**
     * sets the paddle member of this game environment.
     *
     * @param paddle the paddle object in the game
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * getter for this GameEnviroment's obstacles list.
     *
     * @return a lists of obstacles that in this game environment
     */
    public List<Collidable> getObstacles() {
        return obstacles;
    }

    /**
     * gives information about the closest Collision that is going to occur.
     * <p>the Collision is on trajectory line, if there isn't any returns null</p>
     *
     * @param trajectory a line that represents the movement of a ball
     * @return SpritesAndCollidable.CollisionInfo about the closest collision that is going to occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        CollisionInfo info = null;

        for (int i = 0; i < this.obstacles.size(); i++) {
            Point p = trajectory.closestIntersectionToStartOfLine(this.obstacles.get(i).getCollisionRectangle());
            if (info == null && p != null) {
                info = new CollisionInfo(p, this.obstacles.get(i));
            } else if (p != null
                    && trajectory.getStart().distance(p)
                    < trajectory.getStart().distance(info.collisionPoint())) {
                info = new CollisionInfo(p, this.obstacles.get(i));
            }
        }
        return info;
    }

    /**
     * gets the left limit of this game environment.
     *
     * @return this game environment left limit
     */
    public double getLeftLimit() {
        return leftLimit;
    }

    /**
     * gets the right limit of this game environment.
     *
     * @return he right limit of this game environment
     */
    public double getRightLimit() {
        return rightLimit;
    }

    /**
     * gets the up limit of this game environment.
     *
     * @return the up limit of this game environment
     */
    public double getUpLimit() {
        return upLimit;
    }

    /**
     * gets the down limit of this game environment.
     *
     * @return the down limit of this game environment
     */
    public double getDownLimit() {
        return downLimit;
    }

    /**
     * gets the paddle object in this game environment.
     *
     * @return the paddle object in this game environment
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * add the given collidable to this environment's lists of obstacles.
     *
     * @param c the collidable that's going to be added
     */
    public void addCollidable(Collidable c) {
        this.obstacles.add(c);
    }

    /**
     * removes a given collidable from this environment's lists of obstacles.
     *
     * @param c the collidable that's going to be removed
     */
    public void removeCollidable(Collidable c) {
        this.obstacles.remove(c);
    }
}