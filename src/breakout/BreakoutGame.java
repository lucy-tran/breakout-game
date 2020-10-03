package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;

import java.awt.*;
import java.util.List;

/**
 * The game of Breakout.
 *
 * @author Created by Lucy Tran on 11/13/19, with some suggestions from professor Cantrell.
 */
public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private GraphicsGroup group;
    private Ball ball;
    private Paddle paddle;
    public static int brickCount;
    private int times;
    private GraphicsText notifier;

    /**
     * Opens the canvas window, displays the walls, the paddle, brick wall, and reset the game
     * with a ball in the middle of the window. Invokes the canvas to animate and
     * lets the paddle move accordingly to the mouse.
     */
    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        group = new GraphicsGroup();
        times = 3;

        notifier = new GraphicsText("Life count: " + times);
        notifier.setCenter(CANVAS_WIDTH * 0.5, CANVAS_HEIGHT * 0.95);
        notifier.setFontSize(20);
        group.add(notifier);
        canvas.add(group);

        createWallsAndPaddle();
        createBrickWall();

        resetGame();
        canvas.animate(this::updateBallPosition);
        canvas.onMouseMove(event -> {
            paddle.move(event.getDelta().getX());
        });
    }

    /**
     * Resets the game after each turn by setting a new ball in the middle of the canvas.
     * If the ball has no lives left, prints out the notification.
     */
    private void resetGame() {
        if (times == 0) {
            if (brickCount > 0) notifier.setText("Oh noo! You lose T.T");
            else notifier.setText("You win! Congratulations!");
        }
        ball = new Ball(CANVAS_WIDTH * 0.5, CANVAS_HEIGHT * 0.5);
        group.add(ball.getBall());
        canvas.draw();
        pause();
    }

    /**
     * Updates the ball positions if the ball still has life left, and it has not hit all the bricks.
     * Stops the game once all bricks are hit.
     * Once the ball falls down out of the window, resets the game and the ball loses one life.
     */
    private void updateBallPosition() {
        if (times > 0) {
            if (brickCount != 0) {
                ball.testHit(group);
                ball.updatePosition(0.1);
            }
            if (brickCount == 0) {
                notifier.setText("You win! Congratulations!");
            }
        }

        if (ball.getY() > CANVAS_HEIGHT) {
            group.remove(ball.getBall());
            times--;
            notifier.setText("Life count: " + times);
            resetGame();
        }
    }

    private void createWallsAndPaddle() {
        Wall leftWall = new Wall(-100, -100, 115, CANVAS_HEIGHT + 200);
        Wall rightWall = new Wall(CANVAS_WIDTH - 15, -100, 115, CANVAS_HEIGHT + 200);
        Wall ceiling = new Wall(-100, -100, CANVAS_WIDTH + 200, 120);
        group.add(leftWall);
        group.add(rightWall);
        group.add(ceiling);
        paddle = new Paddle();
        paddle.setCenter(CANVAS_WIDTH * 0.5, CANVAS_HEIGHT * 0.9);
        group.add(paddle);
    }

    private void createBrickWall() {
        List<Color> rowColor = List.of(new Color(49, 9, 143), new Color(29, 15, 171),
                new Color(44, 28, 217), new Color(28, 72, 217), new Color(82, 120, 242));
        double y = 100;
        for (int i = 0; i < 10; i++) {
            double x = 22;
            for (int j = 0; j < 10; j++) {
                Brick newBrick = new Brick(x, y, 54, 15, group);
                newBrick.setFillColor(rowColor.get(i / 2));
                group.add(newBrick);
                brickCount++;
                x += 56;
            }
            y += 17;
        }
    }

    private void pause() {
        canvas.pause(2000);
    }

    public static void main(String[] args) {
        new BreakoutGame();
    }

}
