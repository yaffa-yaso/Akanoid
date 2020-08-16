import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(0, 5));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return (new DirectHitBackGround());
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Rectangle rec = new Rectangle(new Point(390, 150),
                20, 20);
        list.add(new Block(rec, Color.RED, "X"));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
