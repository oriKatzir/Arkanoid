package SpritesAndCollidable;

import GameClasses.GameLevel;
import GameClasses.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Sprites.Ball class, a circle that have a center point, radius, color and velocity.
 * <p>a ball also have a game environment member</p>
 *
 * @author ori katzir
 * @version ass5
 * @see Sprite
 * @since 2022/05/22
 */
public class Ball implements Sprite {
    private double radius;
    private Point center;
    private double area;
    private double circumference;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    private static final double MAX_RADIUS = 60;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int LEFT = 3;
    private static final int DOWN = 4;
    private static final int NO_COLLUSION = -1;

    /**
     * constructor for a ball object that uses an existing point.
     *
     * @param center the center point of the new ball
     * @param r      the radius of the new ball
     * @param color  the color the ball is going to be filled with
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        if (r > MAX_RADIUS) {
            r = (int) MAX_RADIUS;
        }
        this.radius = r;
        this.setArea(r);
        this.setCircumference(r);
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * ball constructor that creates a new point object to be the center of it.
     * <p>this constructor uses the point receiving constructor</p>
     *
     * @param x     the x value of the center of the new ball
     * @param y     the y value of the center of the new ball
     * @param r     the radius of the new ball
     * @param color the color the ball is going to be filled with
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * sets the center Geometry.Point of this Sprites.Ball.
     *
     * @param x the new x-axis value of the center point
     * @param y the new y-axis value of the center point
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * sets the area of this ball.
     * <p>the method is private because the area is dependent on the radius</p>
     *
     * @param r the radius of this ball
     */
    private void setArea(int r) {
        this.area = (Math.PI * Math.pow(r, 2));
    }

    /**
     * sets the size, ie the radius of this Sprites.Ball.
     * <p>the changes effects other members of this class</p>
     *
     * @param radius the new radius
     * @see Ball#circumference
     * @see Ball#radius
     */
    public void setSize(double radius) {
        this.radius = radius;
        this.setCircumference((int) this.radius);
        this.setArea((int) this.radius);
    }

    /**
     * sets the circumference of this ball.
     * <p>the method private because circumference is based on the radius</p>
     *
     * @param r the radius of this ball
     */
    private void setCircumference(int r) {
        this.circumference = (2 * Math.PI * r);
    }

    /**
     * sets the velocity of this ball,with two given double data types.
     *
     * @param dx the velocity of the ball on the x-axis
     * @param dy the velocity of the ball on the y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * sets the velocity of this ball,with a given SpritesAndCollidable.Velocity object.
     * <p>uses the setVelocity method that receives two doubles</p>
     *
     * @param v the new velocity of this Sprites.Ball
     */
    public void setVelocity(Velocity v) {
        this.setVelocity(v.getDx(), v.getDy());
    }

    /**
     * gets the center Geometry.Point of this Sprites.Ball.
     *
     * @return a point object
     */
    public Point getCenter() {
        return center;
    }

    /**
     * gets the x value on the x-axis of the center of this ball.
     *
     * @return the x int value if the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * gets the y value on the y-axis of the center of this ball.
     *
     * @return the y int value if the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * gets the radius of this ball.
     *
     * @return a double holds the radius of this ball
     */
    public int getSize() {
        return (int) this.radius;
    }

    /**
     * gets the area of this ball.
     *
     * @return a double, holds the area of the ball
     */
    public double getArea() {
        return this.area;
    }

    /**
     * gets the circumference of this ball.
     *
     * @return a double, holds the circumference of the ball
     */
    public double getCircumference() {
        return this.circumference;
    }

    /**
     * gets the color of this ball.
     *
     * @return a Color object, holds the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * gets the velocity of this ball.
     *
     * @return a SpritesAndCollidable.Velocity object, holds the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * getter for this Sprites.Ball GameClasses.GameEnvironment member.
     *
     * @return this Sprites.Ball GameClasses.GameEnvironment member
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * setter for this GameClasses.GameEnvironment member.
     *
     * @param newEnEnvironment a new GameClasses.GameLevel environment
     */
    public void setEnvironment(GameEnvironment newEnEnvironment) {
        this.environment = newEnEnvironment;
    }

    /**
     * this method sets the changes of the velocity for this Sprites.Ball.
     * <p>the Sprites.Ball's velocity  is bound by this ball game environment</p>
     */
    public void moveOneStep() {
        Line trajectory = findTrajectory();
        CollisionInfo info = this.environment.getClosestCollision(trajectory);

        if (this.center.isThisPointInsideThisRectangle(this.environment.getPaddle().getCollisionRectangle())) {
            moveOneStepPaddle(this.environment.getPaddle());
            return;
        }

        //  if there is no collision
        if (info == null) {
            this.center = velocity.applyToPoint(this.center);
            return;
        }

        //if there is a collision
        Collidable obstacle = info.collisionObject();
        Point collisionP = info.collisionPoint();

        Velocity newVelocity = obstacle.hit(collisionP, this.velocity, this);
        double newValidX = collisionP.getX();
        double newValidY = collisionP.getY();

        int collisionSide = findTheSideThatGotHit(obstacle, collisionP, newVelocity);

        switch (collisionSide) {
            case RIGHT:
                newValidX = collisionP.getX() + 1;
                if (obstacle == this.environment.getPaddle()) {
                    newValidX = this.environment.getPaddle().getCollisionRectangle().getRightSide().getEnd().getX();
                    newValidX += this.environment.getPaddle().getSpeed() + 1;
                }
                break;
            case UP:
                newValidY = collisionP.getY() + 1;
                break;
            case LEFT:
                newValidX = collisionP.getX() - 1;
                if (obstacle == this.environment.getPaddle()) {
                    newValidX = this.environment.getPaddle().getCollisionRectangle().getLeftSide().getEnd().getX();
                    newValidX -= this.environment.getPaddle().getSpeed() + 1;
                }
                break;
            case DOWN:
                newValidY = collisionP.getY() - 1;
                break;
            default:
                break;
        }

        this.velocity.setDy(newVelocity.getDy());
        this.velocity.setDx(newVelocity.getDx());
        this.center.setX(newValidX);
        this.center.setY(newValidY);
    }

    /**
     * this is a move one step method if the ball is inside the paddle.
     *
     * @param paddle the paddle of the game
     */
    private void moveOneStepPaddle(Paddle paddle) {
        Rectangle paddleRect = paddle.getCollisionRectangle();

        Velocity newVelocity = null;
        double newXValue = 0;
        Double newYValue = null;

        if (isThePointInRectCloserToLeftOfRect(paddleRect, this.center)) {
            if (paddleRect.getUpperLeft().getX() - paddle.getSpeed() <= this.environment.getLeftLimit()) {
                newXValue = this.environment.getLeftLimit() + this.radius + 1;
                newYValue = paddleRect.getDownSide().getStart().getY() + 1;
                newVelocity = new Velocity(this.velocity.getDx() * -1, this.velocity.getDy());
            } else {
                newXValue = paddleRect.getUpperLeft().getX() - paddle.getSpeed();
                newVelocity = Velocity.fromAngleAndSpeed(300, this.velocity.findSpeed());
            }

        } else {
            if (paddleRect.getRightSide().getStart().getX() + paddle.getSpeed() >= this.environment.getRightLimit()) {
                newXValue = this.environment.getRightLimit() - this.radius - 1;
                newYValue = paddleRect.getDownSide().getStart().getY() + 1;
                newVelocity = new Velocity(this.velocity.getDx() * -1, this.velocity.getDy());
            } else {
                newXValue = paddleRect.getRightSide().getStart().getX() + paddle.getSpeed();
                newVelocity = Velocity.fromAngleAndSpeed(60, this.velocity.findSpeed());
            }
        }

        if (newYValue == null) {
            newYValue = this.center.getY();
        }
        Point newPosition = newVelocity.applyToPoint(new Point(newXValue, newYValue));
        newPosition = findValidPointOutOfRect(paddleRect, newPosition);
        this.velocity.setDy(newVelocity.getDy());
        this.velocity.setDx(newVelocity.getDx());
        this.center.setX(newPosition.getX());
        this.center.setY(newPosition.getY());
    }

    /**
     * finds the side of an obstacle that the collision point is on.
     *
     * @param obstacle    the collided object
     * @param collisionP  the collision point on the obstacle
     * @param newVelocity the velocity of the ball after the collision
     * @return 1 if right, 2 if up,3 if left, 4 if down, 5 if no collision
     */
    private int findTheSideThatGotHit(Collidable obstacle, Point collisionP, Velocity newVelocity) {

        //if the ball hit a left side or a right side:
        if ((this.velocity.getDx() < 0 && newVelocity.getDx() > 0)
                || (this.velocity.getDx() > 0 && newVelocity.getDx() < 0)) {

            if (obstacle.getCollisionRectangle().getLeftSide().isPointOnThisLine(collisionP)) {
                return LEFT;
            }
            if (obstacle.getCollisionRectangle().getRightSide().isPointOnThisLine(collisionP)) {
                return RIGHT;
            }
        }

        // if the ball hit the top or the flor of the Geometry.Rectangle
        if ((this.velocity.getDy() < 0 && newVelocity.getDy() > 0)
                || (this.velocity.getDy() > 0 && newVelocity.getDy() < 0)) {
            if (obstacle.getCollisionRectangle().getUpperSide().isPointOnThisLine(collisionP)) {
                return UP;
            }
            if (obstacle.getCollisionRectangle().getDownSide().isPointOnThisLine(collisionP)) {
                return DOWN;
            }
        }
        return NO_COLLUSION;
    }

    /**
     * finds a point outside the given rectangle that is inside the limits.
     * <p>the limits are set by the GameClasses.GameEnvironment this ball is in</p>
     *
     * @param rect       the given rectangle
     * @param collisionP a point inside the rectangle
     * @return a point inside the limits
     */
    private Point findValidPointOutOfRect(Rectangle rect, Point collisionP) {
        int closestSide = rect.findSideClosestToPointInRect(collisionP);
        double validXValue = collisionP.getX();
        double validYValue = collisionP.getY();
        switch (closestSide) {
            case RIGHT:
                if (rect.getRightSide().getStart().getX() + this.radius
                        >= this.environment.getRightLimit()) {

                    validXValue = rect.getRightSide().getEnd().getX() - this.radius;

                    if (rect.getRightSide().getEnd().getY() - this.radius
                            <= this.environment.getDownLimit()) {

                        if (rect.getRightSide().getStart().getY() + this.radius
                                < this.environment.getUpLimit()) {
                            validYValue = rect.getRightSide().getEnd().getY() + this.radius;
                        }

                    } else {
                        validYValue = rect.getRightSide().getEnd().getY() - this.radius;
                    }

                } else {
                    validXValue = rect.getRightSide().getEnd().getX() + this.radius;
                }
                break;

            case LEFT:
                if (rect.getLeftSide().getStart().getX() - this.radius
                        <= this.environment.getLeftLimit()) {
                    validXValue = this.environment.getLeftLimit() + this.radius + 1;
                } else {
                    validXValue = rect.getLeftSide().getStart().getX() - this.radius;
                }

                if (rect.getLeftSide().getEnd().getY() - this.radius
                        <= this.environment.getDownLimit()) {

                    validYValue = rect.getLeftSide().getEnd().getY() - this.radius;
                }
                break;

            case UP:
                if (rect.getUpperSide().getStart().getY() + this.radius
                        >= this.environment.getUpLimit()) {
                    validYValue = rect.getDownSide().getStart().getY();
                } else {
                    validYValue = rect.getUpperSide().getStart().getY() + this.radius;
                }
                if (isThePointInRectCloserToLeftOfRect(rect, collisionP)) {
                    if (rect.getLeftSide().getStart().getX() - this.radius
                            > this.environment.getLeftLimit()) {
                        validXValue = rect.getLeftSide().getStart().getX() - this.radius;
                    }
                } else {
                    if (rect.getRightSide().getStart().getX() + this.radius
                            < this.environment.getLeftLimit()) {
                        validXValue = rect.getRightSide().getStart().getX() + this.radius;
                    }
                }

                break;
            case DOWN:
                if (rect.getDownSide().getStart().getY() - this.radius
                        <= this.environment.getDownLimit()) {
                    validYValue = rect.getUpperSide().getStart().getY() + this.radius;
                } else {
                    validYValue = rect.getDownSide().getStart().getY() - this.radius;
                }
                break;
            default:
                return collisionP;
        }
        return new Point(validXValue, validYValue);
    }

    /**
     * check if a given point inside a given rectangle is in its left side.
     *
     * @param rect a rectangle with a point in it
     * @param p    the point inside the rectangle
     * @return true if the point is inside the left side, false if not
     */
    private boolean isThePointInRectCloserToLeftOfRect(Rectangle rect, Point p) {
        if (Math.abs(p.getX() - rect.getLeftSide().getStart().getX())
                <= Math.abs(rect.getRightSide().getStart().getX() - p.getX())) {
            return true;
        }
        return false;
    }

    /**
     * finds the trajectory of this ball.
     *
     * @return a Geometry.Line representing the trajectory of this ball
     */
    private Line findTrajectory() {
        Point newPosition = this.velocity.applyToPoint(this.center);
        return new Line(this.center, newPosition);
    }

    /**
     * A Sprites.Sprite method.
     *
     * @param surface the given DrawSurface
     * @see Sprite#drawOn(biuoop.DrawSurface)
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), (int) this.radius);
    }

    /**
     * A Sprites.Sprite method.
     *
     * @see Sprite#timePassed()
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * A Sprites.Sprite method.
     *
     * @param game the given GameClasses.GameLevel object
     * @see Sprite#addToGame(GameLevel)
     */
    @Override
    public void addToGame(GameLevel game) {
        this.setEnvironment(game.getEnvironment());
        game.addSprite(this);
    }

    /**
     * A method that removes this Ball from the given GameLevel object.
     *
     * @param gameLevel the GameLevel this ball is in
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}