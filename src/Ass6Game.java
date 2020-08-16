import biuoop.GUI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Run game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws IOException {
        GUI gui = new GUI("GameLevel", 800, 600);
        boolean l1 = false, l2 = false, l3 = false, l4 = false;
        List<LevelInformation> levels = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                levels.add(new DirectHit());
                l1 = true;
            } else if (args[i].equals("2")) {
                levels.add(new WideEasy());
                l2 = true;
            } else if (args[i].equals("3")) {
                levels.add(new Green3());
                l3 = true;
            } else if (args[i].equals("4")) {
                levels.add(new FinalFour());
                l4 = true;
            }
        }
        if (!l1 && !l2 && !l3 && !l4) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
        AnimationRunner runner = new AnimationRunner(gui);
        HighScoresTable scores = new HighScoresTable(5);
        //create the high scores table animation
        HighScoresAnimation scoresAnimation = new HighScoresAnimation(scores);
        File f = new File("highscores");
        //save a new HighScores or load an existing one
        if (f.exists()) {
            scores.load(f);
        } else {
            scores.save(f);
        }
        GameFlow game = new GameFlow(runner, gui.getKeyboardSensor(), scores, gui);
        game.runLevels(levels);
        gui.close();
    }
}
