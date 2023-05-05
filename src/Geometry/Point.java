package Geometry;

/**
 * Geometry.Point class, a point is a dot on a coordinate system, holds x and y values.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class Point {
    private double x;
    private double y;
    private static final double EPSILON = Math.pow(10, -10);
    private static final int DEFAULT_X_VALUE = 0;
    private static final int DEFAULT_Y_VALUE = 0;

    /**
     * A constructor for a point object.
     *
     * @param x the x value of the point on the x-axis
     * @param y the y value of the point on the y-axis
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A constructor for a point object,with another Geometry.Point.
     *
     * @param other Geometry.Point thats going to be cooppied into a new Geometry.Point
     */
    public Point(Point other) {
        this(other.getX(), other.getY());
    }

    /**
     * Default constructor.
     */
    public Point() {
        this(DEFAULT_X_VALUE, DEFAULT_Y_VALUE);
    }

    /**
     * calculate the distance 0f this point to the other point.
     *
     * @param other is another point
     * @return the distance between the two points
     */
    public double distance(Point other) {
        double dist = Math.sqrt((this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
        return dist;
    }

    /**
     * checks to see if this point is equal to another point.
     * <p>the check is done to the point of EPSILON</p>
     *
     * @param other is another point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (Math.abs(this.x - other.getX()) <= EPSILON
                && Math.abs(this.y - other.getY()) <= EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * gets the x value of the point on the x-axis.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * sets the x value of the point on the x-axis with new value.
     *
     * @param x the new x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * gets the y value of the point on the y-axis.
     *
     * @return the x value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * sets the y value of the point on the y-axis with new value.
     *
     * @param y the new y value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * checks if this point is inside of a given Geometry.Rectangle.
     *
     * @param rect the given Geometry.Rectangle
     * @return true if the point is inside the Geometry.Rectangle, false if not
     */
    public boolean isThisPointInsideThisRectangle(Rectangle rect) {
        if (this.x >= rect.getUpperLeft().getX()
                && this.x <= rect.getUpperLeft().getX() + rect.getWidth()) {
            if (this.y <= rect.getUpperLeft().getY()
                    && this.y >= rect.getUpperLeft().getY() - rect.getHeight()) {
                return true;
            }
        }
        return false;
    }
}
