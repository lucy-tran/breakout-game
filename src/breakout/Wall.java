package breakout;

import edu.macalester.graphics.Rectangle;

import java.awt.*;

/**
 * Represents a wall in the Breakout Game.
 *
 * @author Created by Lucy Tran on 11/13/19.
 */
public class Wall extends Rectangle implements SolidFace {
    /**
     * Creates a wall in the form of a rectangle
     *
     * @param x the x position of the upper left corner of the wall
     * @param y the y position of the upper left corner of the wall.
     * @param width the wall's width
     * @param height the wall's height
     */
   public Wall(double x, double y, double width, double height){
       super(x, y, width, height);
        this.setStrokeWidth(3);
        this.setFillColor(Color.BLACK);
   }

   @Override
    public void hit(Ball ball, boolean topBottom){
       ball.bounce(topBottom);
    }
}

