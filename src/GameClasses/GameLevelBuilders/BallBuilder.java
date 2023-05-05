package GameClasses.GameLevelBuilders;

import Geometry.Point;
import SpritesAndCollidable.Ball;
import SpritesAndCollidable.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A ball builder, used to build Ball objects with given characteristics.
 * <p>this class used for implementing the Builder pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class BallBuilder {

    private List<Ball> balls = new ArrayList<>();
    private List<Velocity> velocities;
    private int numOfBallsToBuild;
    private Point ballPosition;
    private static final int BALL_RADIUS = 10;

    /**
     * A constructor method.
     *
     * @param numOfBallsToBuild the number of Balls that needs to build
     * @param velocities        a list with a velocity for every ball that needs to be build
     * @param ballPosition      the position that all the balls will spawn on
     */
    public BallBuilder(int numOfBallsToBuild, List<Velocity> velocities, Point ballPosition) {
        this.numOfBallsToBuild = numOfBallsToBuild;
        this.velocities = velocities;
        this.ballPosition = ballPosition;
    }

    /**
     * a method that build the desired balls.
     *
     * @return a List of the desired balls
     */
    public List<Ball> build() {
        for (int i = 0; i < numOfBallsToBuild; i++) {
            Ball ball = new Ball(this.ballPosition, BALL_RADIUS, Color.cyan);
            ball.setVelocity(this.velocities.get(i));
            this.balls.add(ball);
        }
        return this.balls;
    }
}
