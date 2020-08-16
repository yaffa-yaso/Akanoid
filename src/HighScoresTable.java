import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class HighScoresTable implements java.io.Serializable {
    private List<ScoreInfo> scores;
    private int tableSize;

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.scores = new ArrayList<>();
        this.tableSize = size;
    }

    public HighScoresTable() {
        this.scores = new ArrayList<>();
        this.tableSize = 5;
    }

    // Add a high-score.
    public void add(ScoreInfo score) {
        //if score list is empty, add the score
        if (this.scores.size() == 0) {
            this.scores.add(score);
            return;

            //if list has empty slots, insert in the first empty slot
        } else if (this.scores.size() < this.tableSize) {
            for (int i = 0; i < this.scores.size(); i++) {
                if (score.getScore() > this.scores.get(i).getScore()) {
                    this.scores.add(i, score);
                    return;
                }
            }
            this.scores.add(score);
            //else, check if the score is high enough to enter the table
        } else if (this.scores.size() >= this.tableSize) {
            boolean wasAdded = false;
            for (int i = 0; i < tableSize; i++) {
                if (score.getScore() >= this.scores.get(i).getScore()) {
                    this.scores.add(i, score);
                    wasAdded = true;
                    break;
                }
            }
            if (wasAdded) {
                this.scores.remove(this.scores.size() - 1);
            }
        }
    }

    // Return table size.
    public int size() {
        return this.tableSize;
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return new ArrayList<ScoreInfo>(this.scores);
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        int i;

        //in case of an empty table
        if (this.scores.size() == 0) {
            return 1;
        }
        //checking whether or not score should be entered
        for (i = 0; i < this.scores.size(); i++) {
            ScoreInfo current = this.scores.get(i);
            if (current != null) {
                if (score > current.getScore()) {
                    return i + 1;
                }
            }
        }
        if (i < this.tableSize) {
            return i + 1;
        }
        //if score should not be entered, return a size bigger than the table
        return this.tableSize + 1;
    }

    // Clears the table
    public void clear() {
        this.scores.clear();
    }

    // Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        //clear the current table
        this.scores.clear();
        ObjectInputStream is = null;
        try {
            //opening and reading the source file
            is = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable temp = (HighScoresTable) is.readObject();
            //import the loaded file data to the current HighScoresTable
            this.scores = temp.getHighScores();
            this.tableSize = temp.size();
        } catch (IOException e) {
            System.out.println("Error while loading");
        } catch (ClassNotFoundException e) {
            System.out.println("Problem with class");
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Save table data to the specified file.
    public void save(File filename) throws IOException {
        File file = filename;
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        fos.close();
        oos.close();
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(5);
        //in case of a problem with the given file, return an empty one
        if (!filename.canRead() || !filename.exists()) {
            return table;
        } else {
            try {
                table.load(filename);
                return table;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return table;
    }
}