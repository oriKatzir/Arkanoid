package Geometry;

import java.util.List;

/**
 * Geometry.Line class, An finite line set between two Geometry.Point objects.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class Line {
    private Point start;
    private Point end;
    private Double slope;
    private Double intercept;
    private Point pointWithBiggestXValue;
    private Point pointWithSmallestXValue;
    private Point pointWithBiggestYValue;
    private Point pointWithSmallestYValue;
    private static final Double EPSILON = Math.pow(10, -10);

    /**
     * construct a line object between the two given Points objects.
     *
     * @param start a Geometry.Point that marks the start of the line
     * @param end   a point that marks the end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        findSlope();
        setIntercept();
        setPointsRelativeToYAxis();
        setPointsRelativeToXAxis();
    }

    /**
     * construct a line between the two Points that holds the x and y values.
     * <p>this constructor creates two Geometry.Point with Geometry.Point class constructor</p>
     *
     * @param x1 the x value of the start point of the line
     * @param y1 the y value of the start point of the line
     * @param x2 the x value of the end point of the line
     * @param y2 the y value of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * sets the pointWithBiggestYValue, pointWithSmallestYValue of this line.
     */
    private void setPointsRelativeToYAxis() {
        if (this.start.getY() >= this.end.getY()) {
            this.pointWithBiggestYValue = this.start;
            this.pointWithSmallestYValue = this.end;
        } else {
            this.pointWithBiggestYValue = this.end;
            this.pointWithSmallestYValue = this.start;
        }
    }

    /**
     * sets the pointWithBiggestXValue, pointWithSmallestXValue of this line.
     */
    private void setPointsRelativeToXAxis() {
        if (this.start.getX() >= this.end.getX()) {
            this.pointWithBiggestXValue = this.start;
            this.pointWithSmallestXValue = this.end;
        } else {
            this.pointWithBiggestXValue = this.end;
            this.pointWithSmallestXValue = this.start;
        }
    }

    /**
     * returns a Double object tha holds the slope value of the line.
     *
     * @return the slope of this line
     */
    public Double getSlope() {
        return this.slope;
    }

    /**
     * calculate the slope of the line.
     * <p>can hold value that represent positive infinity.</p>
     */
    private void findSlope() {
        if (this.start.getX() == this.end.getX()) {
            this.slope = Double.POSITIVE_INFINITY;
        } else {
            this.slope = (this.start.getY() - this.end.getY())
                    / (this.start.getX() - this.end.getX());
        }
    }

    /**
     * this method used to make the code more readable.
     *
     * @return true if the slope of the line is POSITIVE_INFINITY
     */
    public boolean isVertical() {
        return this.slope.isInfinite();
    }

    /**
     * calculate the y value of the interception of the line with the y-axis.
     * <p>use to calculate line equation, the line may not touch the y axis</p>
     */
    private void setIntercept() {
        this.intercept = this.start.getY() - (this.start.getX() * this.slope);
    }

    /**
     * calculate the length of line with the distance method of Geometry.Point class.
     *
     * @return the distance between the starting point and the end of the line
     * @see Point#distance(Geometry.Point)
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * calculate the middle Geometry.Point of the line.
     *
     * @return the point object representing the middle of the line
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * returns the starting pont of the line.
     *
     * @return the start point
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * returns the ending point of the line.
     *
     * @return the end point
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * checks to see if a given point is on this line.
     * <p>EPSILON used as a small tolerance value for comparing double precision numbers</p>
     *
     * @param p a point suspicious of being on this line
     * @return true if the point is on the line and false otherwise
     */
    public boolean isPointOnThisLine(Point p) {
        if (this.pointWithBiggestXValue.getX() + EPSILON < p.getX()
                || this.pointWithSmallestXValue.getX() - EPSILON > p.getX()) {
            return false;
        }
        if (this.pointWithBiggestYValue.getY() + EPSILON < p.getY()
                || this.pointWithSmallestYValue.getY() - EPSILON > p.getY()) {
            return false;
        }
        return true;
    }

    /**
     * Calculate the y value of a point with the given x value.
     * <p>This method won't get an x value of a vertical line</p>
     *
     * @param x an x-axis value
     * @return y value of the point on the line that holds the given x value
     */
    private double getAppropriateYValueForXValue(double x) {
        return ((this.slope * x) + this.intercept);
    }

    /**
     * finds a potential intersecting point between this line and another line.
     * <p>this method uses the lines equations</p>
     *
     * @param other another Geometry.Line
     * @return new Geometry.Point object that represents a possible intersection point
     */
    private Point findPossibleIntersectP(Line other) {
        double checkedYValue;
        double checkedXValue;
        if (this.isVertical()) {
            checkedXValue = this.start.getX();
            checkedYValue = other.getAppropriateYValueForXValue(checkedXValue);
        } else {
            if (other.isVertical()) {
                checkedXValue = other.start.getX();
            } else {
                checkedXValue = (this.intercept - other.intercept)
                        / (other.slope - this.slope);
            }
            checkedYValue = this.getAppropriateYValueForXValue(checkedXValue);
        }
        return new Point(checkedXValue, checkedYValue);
    }

    /**
     * This method checks if this line intersect with another given line.
     *
     * @param other another Geometry.Line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
            return true;
        }
        if (this.slope.equals(other.getSlope())) {
            if (this.length() > other.length()) {
                return isPointOnThisLine(other.getStart())
                        || isPointOnThisLine(other.getEnd());
            } else {
                return other.isPointOnThisLine(this.start)
                        || other.isPointOnThisLine(this.end);
            }
        }
        Point pointToCheck = this.findPossibleIntersectP(other);
        if (isPointOnThisLine(pointToCheck)) {
            pointToCheck = other.findPossibleIntersectP(this);
            return other.isPointOnThisLine(pointToCheck);
        }
        return false;
    }

    /**
     * Returns the intersection point if the lines intersect.
     *
     * @param other another Geometry.Line object
     * @return Geometry.Point if there is an intersection and null if there isn't any
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other) || !isIntersecting(other)) {
            return null;
        }

        // if the lines are overlapping
        if (this.slope.equals(other.getSlope())) {

            //if the starting point of this line is touching the other line
            if (this.start.equals(other.getStart())
                    || this.start.equals(other.end)) {
                return this.start;
            }

            //if the ending point of this line is touching the other line
            if (this.end.equals(other.getStart())
                    || this.end.equals(other.getEnd())) {
                return this.end;
            }
            return null;
        }
        return findPossibleIntersectP(other);
    }


    /**
     * Checks if this Geometry.Line is equal to another Geometry.Line.
     *
     * @param other another Geometry.Line object
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.slope.equals(other.getSlope())
                || this.slope.equals(-other.getSlope())) {
            if ((this.start.equals(other.getStart())
                    || this.start.equals(other.getEnd()))
                    && this.length() == other.length()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the closest intersection point to the start of this line.
     * <p>If this line does not intersect with the rectangle, return null</p>
     *
     * @param rect given rectangle to find intersection points with
     * @return the closest intersection point to the start of this line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        if (list.isEmpty()) {
            return null;
        }
        return this.findClosestPointToStart(list);
    }

    /**
     * fides the closest point to the start of this line in a list points.
     *
     * @param list list of points to find the closest one from
     * @return the closest point to the start of this line
     */
    public Point findClosestPointToStart(List<Point> list) {
        if (list.isEmpty()) {
            return null;
        }
        Point minPoint = new Point(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if (this.start.distance(minPoint)
                    > this.start.distance(list.get(i))) {
                minPoint = list.get(i);
            }
        }
        return minPoint;
    }
}
