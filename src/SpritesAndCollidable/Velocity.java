package SpritesAndCollidable;

import Geometry.Point;

/**
 * SpritesAndCollidable.Velocity class, object that holds velocity values on the x-axis and y-axis.
 *
 * @author ori katzir
 * @version ass3
 * @since 2022/04/01
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor method, create a new SpritesAndCollidable.Velocity object with two given doubles.
     *
     * @param dx a double, represents velocity on the x-axis
     * @param dy a double, represents velocity on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * creates a new velocity with an angle and speed.
     *
     * @param angle a double represents the angle of the velocity vector
     * @param speed a double represents the speed of an object
     * @return a new velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * (-Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * calculate the speed of the objects that holds this velocity.
     *
     * @return the speed of the objects that holds this velocity
     */
    public double findSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * gets the velocity on the x-axis.
     *
     * @return a double, represents velocity on the x-axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * sets the velocity component on the x-axis of this velocity.
     *
     * @param dx the velocity component on the x-axis
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * gets the velocity on the y-axis.
     *
     * @return a double, represents velocity on the y-axis
     */
    public double getDy() {
        return dy;
    }

    /**
     * sets the velocity component on the y-axis of this velocity.
     *
     * @param dy the velocity component on the y-axis
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take a point with position and return a new point with a new position.
     * <p>if the old position was (x,y), the new one will be (x+dx, y+dy)</p>
     *
     * @param p a Geometry.Point in the old position
     * @return a new Geometry.Point in a new position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}