import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int angle = -30;
        for (int i = 0; i < 2; i++) {
            list.add(Velocity.fromAngleAndSpeed(angle, 5));
            angle += 60;
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3BackGround();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        int x = 720, y = 150;
        Color color = Color.RED;
        int p = 10;
        for (int i = 5; i > 0; i--) {
            for (int j = p; j > 0; j--) {
                Rectangle rec = new Rectangle(new Point(x, y),
                        60, 20);
                list.add(new Block(rec, color, "X"));
                x -= 60;
            }
            x = 720;
            y += 20;
            p--;
            if (i == 2) {
                color = Color.BLUE;
            } else if (i == 3) {
                color = Color.PINK;
            } else if (i == 4) {
                color = Color.YELLOW;
            } else if (i == 5) {
                color = Color.orange;
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
