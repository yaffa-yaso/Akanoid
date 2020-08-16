import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;

public class HighScoresAnimation implements Animation {
    private HighScoresTable table;
    private boolean stop;

    public HighScoresAnimation(HighScoresTable scores) {
        this.table = scores;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(45, 25, "High Scores", 30);
        d.setColor(Color.YELLOW);
        d.drawText(46, 26, "High Scores", 29);

        d.setColor(Color.GREEN.brighter());
        d.drawText(100, 100, "Player Name", 25);
        d.drawText(420, 100, "Scores", 25);
        d.drawRectangle(100, 130, 450, 4);
        d.fillRectangle(100, 130, 450, 4);

        int nameStartX = 100;
        int startY = 160;
        int scoreStartX = 420;
        List<ScoreInfo> scores = this.table.getHighScores();
        d.setColor(Color.RED.brighter());
        for (ScoreInfo current : scores) {
            d.drawText(nameStartX, startY, current.getName(), 23);
            Integer toStr = new Integer(current.getScore());
            d.drawText(scoreStartX, startY, toStr.toString(), 23);
            startY += 48;
        }
        int width = d.getWidth() / 2;
        int height = d.getHeight() - 120;
        d.drawText(width - 150 , height, "Press SPACE to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}