package breakout;

/**
 * Represents the general/common behaviour (hit) of all solid faces in the Breakout Game - walls, bricks and a paddle.
 *
 * @author Created by Lucy Tran on 11/13/19.
 */
public interface SolidFace {
    void hit(Ball ball, boolean topBottom);
}

