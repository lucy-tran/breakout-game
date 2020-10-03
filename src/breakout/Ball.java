package breakout;

import edu.macalester.graphics.*;
import edu.macalester.graphics.Point;

import java.util.Random;

import static java.awt.Color.*;

/**
 * Represents the ball of the game. Bounces off solid faces whenever there is one it meets.
 *
 * @author Created by Lucy Tran on 11/13/19,
 *
 * @acknowledgement Ideas of updating the angle are suggested by professor Cantrell.
 */
public class Ball {
    private double angle;
    private double angleInRadians;
    private double centerX;
    private double centerY;
    private double dX;
    private double dY;
    private Random random;
    private Ellipse ball;
    private final double SPEED = 80;
    private final double DIAMETER = 12;

    /**
     * Creates a ball with a random angle directed downwards.
     *
     * @param x x position of the ball on canvas
     * @param y y position of the ball on canvas
     */
    public Ball(double x, double y) {
        this.centerX = x;
        this.centerY = y;
        random = new Random();
        angle = random.nextInt(120) + 210; //so that the initial angle is between 210 and 330 degree,
                                                    // which makes it easier to play
        angleInRadians = Math.toRadians(angle);
        ball = new Ellipse(0, 0, DIAMETER, DIAMETER);
        ball.setCenter(centerX, centerY);
        ball.setFillColor(BLACK);

        dX = SPEED * Math.cos(angleInRadians);
        dY = SPEED * -Math.sin(angleInRadians);
    }

    /**
     * Bounces the ball based on the side it is hit by a solid face.
     *
     * @param topBottom represents whether the ball is hit at the top/bottom side or not.
     */
    public void bounce(boolean topBottom) {
        if (topBottom) dY = -dY;
        else dX = -dX;
    }

    /**
     * Updates the ball position in each interval dt
     *
     * @param dt the interval amount of time between each update
     */
    public void updatePosition(double dt) {
        centerX = centerX + dX * dt;
        centerY = centerY + dY * dt;
        ball.setCenter(centerX, centerY);
    }

    /**
     * @return the ball shape
     */
    public Ellipse getBall() {
        return ball;
    }

    /**
     * @return the y position of the upper left corner of the rectangle frame containing the ball shape.
     */
    public double getY() {
        return ball.getY();
    }

    /**
     * Tests whether the ball is hit on any sides of it
     *
     * @param group the graphics group containing the ball
     */
    public void testHit(GraphicsGroup group) {
        Point upperSide = new Point(ball.getX() + DIAMETER / 2, ball.getY() - 0.2);
        Point lowerSide = new Point(ball.getX() + DIAMETER / 2, ball.getY() + DIAMETER + 0.2);
        Point leftSide = new Point(ball.getX() - 0.2, ball.getY() + DIAMETER / 2);
        Point rightSide = new Point(ball.getX() + DIAMETER + 0.2, ball.getY() + DIAMETER / 2);
        testHit(group, upperSide);
        testHit(group, lowerSide);
        testHit(group, leftSide);
        testHit(group, rightSide);

    }

    /**
     * Tests whether the close surrounding area of the ball is a solid face.
     * If yes, calls the solid face to hit the ball.
     *
     * @param group the graphics group containing the ball
     * @param point the location of the side of the ball
     */
    private void testHit(GraphicsGroup group, Point point) {
        GraphicsObject obj = group.getElementAt(point);
        if (obj instanceof SolidFace) {
            if (point.getY() < ball.getY() || point.getY() > ball.getY() + DIAMETER)
                ((SolidFace) obj).hit(this, true);
            if (point.getX() < ball.getX() || point.getX() > ball.getX() + DIAMETER)
                ((SolidFace) obj).hit(this, false);
        }
    }
}
