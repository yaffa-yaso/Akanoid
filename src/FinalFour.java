import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Final four.
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int angle = -30;
        for (int i = 0; i < 3; i++) {
            list.add(Velocity.fromAngleAndSpeed(angle, 5));
            angle += 30;
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackGround();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        int y = 150;
        Color color = Color.RED;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Rectangle rec = new Rectangle(new Point(20 + j * 50.677, y),
                        50.677, 20);
                list.add(new Block(rec, color, "X"));
            }
            y += 20;
            if (i == 0) {
                color = Color.BLUE;
            } else if (i == 1) {
                color = Color.PINK;
            } else if (i == 2) {
                color = Color.YELLOW;
            } else if (i == 3) {
                color = Color.orange;
            } else if (i == 4) {
                color = Color.LIGHT_GRAY;
            } else if (i == 5) {
                color = Color.white;
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
