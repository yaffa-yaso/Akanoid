import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int angle = -80;
        for (int i = 0; i < 15; i++) {
            list.add(Velocity.fromAngleAndSpeed(angle, 5));
            if (i != 4) {
                angle += 20;
            }
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 720;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackGround();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Color color = Color.RED;
        for (int i = 0; i < 15; i++) {
            Rectangle rec = new Rectangle(new Point(20 + i * 50.677, 250),
                    50.677, 20);
            list.add(new Block(rec, color, "X"));
            if (i == 1) {
                color = Color.BLUE;
            } else if (i == 3) {
                color = Color.PINK;
            } else if (i == 5) {
                color = Color.YELLOW;
            } else if (i == 8) {
                color = Color.orange;
            } else if (i == 10) {
                color = Color.GREEN;
            } else if (i == 12) {
                color = Color.LIGHT_GRAY;
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
