package breakout;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

import static breakout.BreakoutGame.brickCount;

/**
 * Represents a brick in the Breakout Game.
 *
 * @author Created by Lucy Tran on 11/13/19.
 */
public class Brick extends Rectangle implements SolidFace {
    private GraphicsGroup group;

    /**
     *
     * @param x the x position of the upper left corner of the brick
     * @param y the y position of the upper left corner of the brick
     * @param width the brick's width
     * @param height the brick's height
     * @param group the graphic group containing the brick
     */
    public Brick(double x, double y, double width, double height, GraphicsGroup group) {
        super(x, y, width, height);
        this.group = group;
    }

    @Override
    public void hit(Ball ball, boolean topBottom) {
        ball.bounce(topBottom);
        group.remove(this);
        brickCount--;
    }
}
