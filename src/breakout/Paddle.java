package breakout;

import edu.macalester.graphics.Rectangle;

import static java.awt.Color.*;

import java.awt.Color;

/**
 * Represents the paddle in the Breakout Game
 *
 * @author Created by Lucy Tran on 11/13/19.
 */
public class Paddle extends Rectangle implements SolidFace {
    /**
     * Creates a paddle in the form of a rectangle.
     */
    public Paddle() {
        super(0, 0, 100, 10);
        this.setFillColor(Color.BLACK);
        this.setFilled(true);
    }

    @Override
    public void hit(Ball ball, boolean topBottom) {
        ball.bounce(true);
    }

    /**
     * Moves the paddle horizontally according to the mouse movement.
     * Prevents the paddle to move outside of the side walls.
     *
     * @param deltaX the x amount of mouse move.
     */
    public void move(double deltaX) {
        if (this.getX() + deltaX + this.getWidth() < 585 && this.getX() + deltaX > 15)
            this.setPosition(this.getX() + deltaX, this.getY());
    }
}
