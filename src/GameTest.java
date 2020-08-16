import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * The type GameLevel test.
 */
public class GameTest {
    private static GameEnvironment game;
    private static SpriteCollection sprite;

    /**
     * create a ball with a random location, draws it on the surface and moves it around.
     *
     * @param args to main
     */
    public static void main(String[] args) {
        Block[] bArr = new Block[10];
        Random rand = new Random();
        GUI gui = new GUI("title", 800, 600);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(400, 500, 30, Color.BLACK, new GameEnvironment());
        sprite.addSprite(ball);
        ball.frameBoundaries(new Point(0, 0), new Point(200, 200));
        Velocity v = Velocity.fromAngleAndSpeed(50, 5);
        ball.setVelocity(v);
        for (int i = 0; i < 10; i++) {
            Rectangle rec = new Rectangle(new Point(rand.nextInt(600) + 1, rand.nextInt(600) + 1),
                    200, 75);
            Block block = new Block(rec, new Color((int) (Math.random() * 0x1000000)), "X");
            game.addCollidable(block);
            sprite.addSprite(block);
            bArr[i] = block;
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            sprite.notifyAllTimePassed();
            sprite.drawAllOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
