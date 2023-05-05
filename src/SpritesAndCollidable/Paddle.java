package SpritesAndCollidable;

import GameClasses.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * A Sprites.Paddle class, this is an object that played by the player.
 *
 * @author ori katzir
 * @version ass5
 * @see Collidable
 * @see Sprite
 * @since 2022/05/22
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;

    public static final double STATIC_Y_VALUE_OF_UPPER_SIDE = 560;
    private static final double X_STARTING_VALUE = 400;
    private double frameWidth;
    private Point upperLeft;
    private double width;
    private double height = 15;
    private Color color = Color.red;
    private double firstRegionStart;
    private double firstRegionEnd;
    private double secondRegionStart;
    private double secondRegionEnd;
    private double thirdRegionStart;
    private double thirdRegionEnd;
    private double forthRegionStart;
    private double forthRegionEnd;
    private double fifthRegionStart;
    private double fifthRegionEnd;
    private int speed;
    private static final int FIRST_REGION = 1;
    private static final int SECOND_REGION = 2;
    private static final int THIRD_REGION = 3;
    private static final int FORTH_REGION = 4;
    private static final int FIFTH_REGION = 5;
    private static final int FIRST_REGION_ANGLE = 300;
    private static final int SECOND_REGION_ANGLE = 330;
    private static final int THIRD_REGION_ANGLE = 0;
    private static final int FORTH_REGION_ANGLE = 30;
    private static final int FIFTH_REGION_ANGLE = 60;
    private static final int NO_REGION = -1;
    private static final int DEFAULT_SPEED = 10;
    private static final int WIDTH_OF_WALL = 20;
    private static final double NUMBER_OF_REGIONS = 5;
    private static final double HEIGHT_BUFFER = 20;


    /**
     * * A constructor method, sets the members of this Sprites.Paddle.
     *
     * @param keyboard   a keyboarde sensor, used to get commands from the player
     * @param frameWidth the width of the frame of the game this Sprites.Paddle is in
     * @param upperLeft  the upper left point in the paddle's rectangle
     * @param width      the width of the paddle
     * @param height     the height of the paddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard, double frameWidth, Point upperLeft, double width, double height) {
        this.upperLeft = new Point(X_STARTING_VALUE, STATIC_Y_VALUE_OF_UPPER_SIDE + this.height);
        this.height = height;
        this.width = width;
        this.rect = new Rectangle(upperLeft, this.width, this.height);
        this.keyboard = keyboard;
        this.frameWidth = frameWidth;
        this.speed = DEFAULT_SPEED;
    }

    /**
     * A setter for this paddle speed member.
     *
     * @param newSpeed a new speed for this paddle
     */
    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }


    /**
     * this method finds the center point of this paddle's downside.
     *
     * @return the center point of this paddle's downside
     */
    public Point getCenterPointOfDownSide() {
        double centerXValue = this.upperLeft.getX();
        return new Point(centerXValue, (STATIC_Y_VALUE_OF_UPPER_SIDE - HEIGHT_BUFFER));
    }

    /**
     * moves this paddle to the left.
     *
     * @see Rectangle#moveRectByUpperLeftP(double, double)
     */
    public void moveLeft() {
        if (this.rect.getUpperLeft().getX() - this.speed >= WIDTH_OF_WALL) {
            this.rect.moveRectByUpperLeftP(this.rect.getUpperLeft().getX()
                    - this.speed, STATIC_Y_VALUE_OF_UPPER_SIDE);
        } else {
            this.rect.moveRectByUpperLeftP(WIDTH_OF_WALL, STATIC_Y_VALUE_OF_UPPER_SIDE);
        }
    }

    /**
     * moves this paddle to the right.
     *
     * @see Rectangle#moveRectByUpperLeftP(double, double)
     */
    public void moveRight() {
        if (this.rect.getUpperLeft().getX() + this.width + this.speed
                <= this.frameWidth - WIDTH_OF_WALL) {
            this.rect.moveRectByUpperLeftP(this.rect.getUpperLeft().getX()
                    + this.speed, STATIC_Y_VALUE_OF_UPPER_SIDE);
        } else {
            this.rect.moveRectByUpperLeftP(this.frameWidth - WIDTH_OF_WALL
                    - this.width, STATIC_Y_VALUE_OF_UPPER_SIDE);
        }
    }

    // Sprites.Sprite

    /**
     * A Sprites.Sprite method.
     *
     * @see Sprite#timePassed()
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
        findRegions();
    }

    /**
     * A Sprites.Sprite method.
     *
     * @param d the given DrawSurface
     * @see Sprite#drawOn(biuoop.DrawSurface)
     */
    public void drawOn(biuoop.DrawSurface d) {
        Block b = new Block(this.rect, this.color);
        b.drawOn(d);
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

    /**
     * fides the 5 regions the paddle is divided to, and sets it region members.
     */
    private void findRegions() {
        double regionLength = (this.width) / NUMBER_OF_REGIONS;
        firstRegionStart = this.rect.getUpperLeft().getX();
        firstRegionEnd = this.firstRegionStart + regionLength;
        secondRegionStart = firstRegionEnd;
        secondRegionEnd = secondRegionStart + regionLength;
        thirdRegionStart = secondRegionEnd;
        thirdRegionEnd = thirdRegionStart + regionLength;
        forthRegionStart = thirdRegionEnd;
        forthRegionEnd = forthRegionStart + regionLength;
        fifthRegionStart = forthRegionEnd;
        fifthRegionEnd = fifthRegionStart + regionLength;

    }

    /**
     * fides the region of a point belongs to by its x-axis value.
     * <p> this method returns -1 if the point is not on top of the paddle</p>
     *
     * @param x an x-axis value of a point on the paddle
     * @return the number of the region
     */
    private int findWhatRegionThePointIs(double x) {

        if (x > firstRegionStart && x < firstRegionEnd) {
            return FIRST_REGION;
        }
        if (x >= secondRegionStart && x < secondRegionEnd) {
            return SECOND_REGION;
        }
        if (x >= thirdRegionStart && x < thirdRegionEnd) {
            return THIRD_REGION;
        }
        if (x >= forthRegionStart && x < forthRegionEnd) {
            return FORTH_REGION;
        }
        if (x >= fifthRegionStart && x < fifthRegionEnd) {
            return FIFTH_REGION;
        }
        return NO_REGION;
    }

    // SpritesAndCollidable.Collidable

    /**
     * A SpritesAndCollidable.Collidable method.
     *
     * @return the object's Geometry.Rectangle member
     * @see Collidable#getCollisionRectangle()
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * getter for this Sprites.Paddle's speed.
     *
     * @return this Sprites.Paddle's speed
     */
    public double getSpeed() {
        return this.speed;
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
        double speed = currentVelocity.findSpeed();

        if (this.rect.getDownSide().isPointOnThisLine(collisionPoint)) {
            int region = findWhatRegionThePointIs(collisionPoint.getX());

            if (region == FIRST_REGION) {
                currentVelocity = Velocity.fromAngleAndSpeed(FIRST_REGION_ANGLE, speed);
            } else if (region == SECOND_REGION) {
                currentVelocity = Velocity.fromAngleAndSpeed(SECOND_REGION_ANGLE, speed);
            } else if (region == THIRD_REGION) {
                currentVelocity = Velocity.fromAngleAndSpeed(THIRD_REGION_ANGLE, speed);
            } else if (region == FORTH_REGION) {
                currentVelocity = Velocity.fromAngleAndSpeed(FORTH_REGION_ANGLE, speed);
            } else if (region == FIFTH_REGION) {
                currentVelocity = Velocity.fromAngleAndSpeed(FIFTH_REGION_ANGLE, speed);
            }
        } else {
            if (this.rect.getLeftSide().isPointOnThisLine(collisionPoint)) {

                // we hit the left side of the paddle;
                currentVelocity = Velocity.fromAngleAndSpeed(FIRST_REGION_ANGLE, speed);

            } else if (this.rect.getRightSide().isPointOnThisLine((collisionPoint))) {

                //we hit the right side of the paddle
                currentVelocity = Velocity.fromAngleAndSpeed(FIFTH_REGION_ANGLE, speed);

            }
        }
        return currentVelocity;
    }
}