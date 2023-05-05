package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * A Geometry.Rectangle class, a Geometry.Rectangle that is the "skeleton" of an object.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class Rectangle {

    private Line upperSide;
    private Line downSide;
    private Line leftSide;
    private Line rightSide;
    private Point upperLeftP;
    private double width;
    private double height;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int LEFT = 3;
    private static final int DOWN = 4;
    private static final int OUT_OF_BOUND = -1;

    /**
     * a constructor of a Geometry.Rectangle, sets its members.
     * <p>uses an point, width and height</p>
     *
     * @param upperLeft the upper left point of this rectangle
     * @param width     the width of this rectangle
     * @param height    the height of this rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeftP = new Point(upperLeft);
        this.width = width;
        this.height = height;
        findSidesOfRectangle();
    }


    /**
     * a constructor of a Geometry.Rectangle, sets its members.
     * <p>makes a copy of a given rectangle</p>
     *
     * @param rec rectangle to be copied
     */
    public Rectangle(Rectangle rec) {
        this(rec.getUpperLeft(), rec.getWidth(), rec.getHeight());
    }

    /**
     * A setter for this Rectangle width member.
     *
     * @param width a new width
     */
    public void setWidth(double width) {
        this.width = width;
        findSidesOfRectangle();
    }

    /**
     * a setter for the height member of this rectangle.
     *
     * @param height a new height
     */
    public void setHeight(double height) {
        this.height = height;
        findSidesOfRectangle();
    }


    /**
     * moves this rectangle to new location by a new upper left point.
     *
     * @param x a new x value for this rectangle upper left point
     * @param y a new y value for this rectangle upper left point
     */
    public void moveRectByUpperLeftP(double x, double y) {
        this.upperLeftP.setX(x);
        this.upperLeftP.setY(y);
        findSidesOfRectangle();
    }

    /**
     * moves this rectangle to new location by a new upper left point.
     *
     * @param newUpperLeftP a new upperLeft point for this rectangle
     */
    public void moveRectByUpperLeftP(Point newUpperLeftP) {
        this.moveRectByUpperLeftP(newUpperLeftP.getX(), newUpperLeftP.getY());
    }

    /**
     * finds and set this rectangle's sides members.
     */
    private void findSidesOfRectangle() {
        double upperLeftX = this.upperLeftP.getX();
        double upperLeftY = this.upperLeftP.getY();
        this.upperSide = new Line(upperLeftX, upperLeftY,
                upperLeftX + this.width, upperLeftY);
        this.downSide = new Line(upperLeftX, upperLeftY - this.height,
                upperLeftX + this.width, upperLeftY - this.height);
        this.rightSide = new Line(upperLeftX + this.width, upperLeftY,
                upperLeftX + this.width, upperLeftY - this.height);
        this.leftSide = new Line(upperLeftX, upperLeftY, upperLeftX,
                upperLeftY - this.height);
    }

    /**
     * finds this rectangle intersection points with a given line.
     * <p>if there isn't any intersection null will be returned</p>
     *
     * @param line the line we want to find in intersection points
     * @return a list of the intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> interPointsList = new ArrayList<>();
        if (this.upperSide.isIntersecting(line)) {
            interPointsList.add(findIntersectionPoint(this.upperSide, line));
        }
        if (this.downSide.isIntersecting(line)) {
            interPointsList.add(findIntersectionPoint(this.downSide, line));
        }
        if (this.leftSide.isIntersecting(line)) {
            interPointsList.add(findIntersectionPoint(this.leftSide, line));
        }
        if (this.rightSide.isIntersecting(line)) {
            interPointsList.add(findIntersectionPoint(this.rightSide, line));
        }
        return interPointsList;
    }

    /**
     * finds intersection point of a given side of this rectangle and a line.
     * <p>if there isn't any intersection null will be returned</p>
     *
     * @param rectangleSide a side of this rectangle
     * @param line          the line we want to find in intersection points
     * @return an intersection point
     */
    private Point findIntersectionPoint(Line rectangleSide, Line line) {
        Point newPoint = rectangleSide.intersectionWith(line);

        //in case of containment
        if (newPoint == null) {
            if (line.getStart().distance(rectangleSide.getStart())
                    <= line.getStart().distance(rectangleSide.getEnd())) {
                newPoint = rectangleSide.getStart();
            } else {
                newPoint = rectangleSide.getEnd();
            }
        }
        return newPoint;
    }

    /**
     * Return the width of this rectangle.
     *
     * @return the width of this rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of this rectangle.
     *
     * @return the height of this rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getter of the downside of this rectangle.
     *
     * @return the downside of this rectangle
     */
    public Line getDownSide() {
        return this.downSide;
    }

    /**
     * getter of the upper side of this rectangle.
     *
     * @return the upper side of this rectangle
     */
    public Line getUpperSide() {
        return this.upperSide;
    }

    /**
     * getter of the left side of this rectangle.
     *
     * @return the left side of this rectangle
     */
    public Line getLeftSide() {
        return this.leftSide;
    }

    /**
     * getter of the right side of this rectangle.
     *
     * @return the right side of this rectangle
     */
    public Line getRightSide() {
        return this.rightSide;
    }

    /**
     * getter of the upper-left point of this rectangle.
     *
     * @return the upper-left point of this rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeftP;
    }

    /**
     * checks if the given point is on any one of the sides of this rectangle.
     *
     * @param givenPoint the point to check if is on this rectangle sides
     * @return true if the poin is on a side of this rectangle, false if not
     */
    public boolean isPointOnThisRect(Point givenPoint) {
        return this.downSide.isPointOnThisLine(givenPoint)
                || this.upperSide.isPointOnThisLine(givenPoint)
                || this.rightSide.isPointOnThisLine(givenPoint)
                || this.leftSide.isPointOnThisLine(givenPoint);
    }

    /**
     * finds the side of this rectangle that is closest to the given point.
     * <p>works only for point inside the rectangle</p>
     *
     * @param p the given point
     * @return 1 if right,2 if up, 3 if left, 4 if down and -1 if point isn't in this rect
     */
    public int findSideClosestToPointInRect(Point p) {
        if (p.isThisPointInsideThisRectangle(this)) {

            //if the point is closer to the left side
            if (p.getX() <= this.upperSide.middle().getX()) {

                // if the point is closer to the downside of this rectangle
                if (p.getY() <= this.leftSide.middle().getY()) {

                    // if the point is closer to the left side then the downside
                    if (p.getX() - this.leftSide.getStart().getX()
                            <= p.getY() - this.downSide.getStart().getY()) {
                        return LEFT;
                    } else {

                        //if the point is closer to the downside then the left side
                        return DOWN;
                    }

                    // if the point is closer to the upper side of this rectangle
                } else {

                    //if the point is closer to the left side then the upper side
                    if (p.getX() - this.leftSide.getStart().getX()
                            <= this.upperSide.getStart().getY() - p.getY()) {
                        return LEFT;
                    } else {

                        //if the point is closer to the upper side then the left side
                        return UP;
                    }
                }
            } else { // if the point is closer to the right side

                // if the point is closer to the downside of this rectangle
                if (p.getY() <= this.rightSide.middle().getY()) {

                    //if the point is closer to the downside then the right side
                    if (this.rightSide.getEnd().getX() - p.getX()
                            >= p.getY() - this.downSide.getEnd().getY()) {
                        return DOWN;
                    } else {

                        //if the point is closer to the right side the the down side
                        return RIGHT;
                    }
                } else { // if the point is closer to the upper side of this rectangle

                    // if the point is closer to the upper side them the right side
                    if (this.upperSide.getEnd().getY() - p.getY()
                            <= this.rightSide.getStart().getX() - p.getX()) {
                        return UP;
                    } else {
                        return RIGHT;
                    }
                }
            }
        } else {
            return OUT_OF_BOUND;
        }
    }
}

