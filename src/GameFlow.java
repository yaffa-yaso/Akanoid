import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter numOfLives;
    private Counter totalScore;
    private HighScoresTable scoresTable;
    private GUI gui;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, biuoop.KeyboardSensor ks, HighScoresTable scores, GUI g) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.totalScore = new Counter(0);
        this.numOfLives = new Counter(7);
        this.scoresTable = scores;
        this.gui = g;
    }

    /**
     * This method returns true if game was won
     * (all levels were finished, and the amount of live left
     * is bigger than 0.
     *
     * @return boolean true if game was won, false otherwise
     */
    public boolean trueIfWon() {
        //if lives were left, means game was won
        if (this.numOfLives.getValue() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.totalScore, this.numOfLives);

            level.initialize();

            while (level.leftBlocks() != 0 && level.leftLives() != 0) {
                level.playOneTurn();
            }
        }
        //highScore table check
        if (scoresTable.getRank(this.totalScore.getValue()) <= scoresTable.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.scoresTable.add(new ScoreInfo(name, this.totalScore.getValue()));
        }

        //showing the end screen when all levels were finished
        EndScreen end = new EndScreen(this.numOfLives, this.totalScore);
        HighScoresAnimation highScores = new HighScoresAnimation(this.scoresTable);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", end));
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", highScores));
    }
}