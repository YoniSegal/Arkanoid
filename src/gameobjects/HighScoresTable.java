package gameobjects;

import gamelogic.ScoreInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class creates a highscores table.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> scoreInfos = new ArrayList<>();

    /**
     * Create an empty high-scores table with the specified size. The size means
     * that the table holds up to size top scores.
     *
     * @param size int.
     */
    public HighScoresTable(int size) {
        this.size = size;
    }

    /**
     * Add a high-score.
     *
     * @param score ScoreInfo.
     */
    public void add(ScoreInfo score) {
        if (getRank(score.getScore()) > size) {
            return;
        }
        scoreInfos.add(getRank(score.getScore()) - 1, score);
    }

    /**
     * Return table size.
     *
     * @return int.
     */
    public int size() {
        return size;
    }

    /**
     * Return the current high scores. The list is sorted such that the highest
     * scores come first.
     *
     * @return List of scoresInfos.
     */
    public List<ScoreInfo> getHighScores() {
        return scoreInfos;
    }

    /**
     * return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score int.
     * @return int.
     */
    public int getRank(int score) {
        int i;
        for (i = 0; i < scoreInfos.size(); i++) {
            if (score > scoreInfos.get(i).getScore()) {
                break;
            }
        }
        return i + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        scoreInfos.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     *
     * @param filename File.
     * @throws IOException exception.
     */
    public void load(File filename) throws IOException {
        try {
            this.scoreInfos = loadFromFile(filename).getHighScores();
            this.size = loadFromFile(filename).size;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename File.
     * @throws IOException exception.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            System.out.println("Failed to save object to file: " + filename);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println("Error savng file: " + filename);
            }
        }
    }

    /**
     * Read a table from file and return it. If the file does not exist, or
     * there is a problem with reading it, an empty table is returned.
     *
     * @param filename File.
     * @return HighScoresTable.
     * @throws IOException exception.
     */
    public static HighScoresTable loadFromFile(File filename) throws IOException {
        HighScoresTable scores;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            scores = (HighScoresTable) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file: " + filename);
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to find class in file: " + filename);
            return null;
        } catch (IOException e) {
            System.out.println("Exception in file: " + filename);
            return null;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.out.println("Failed to close file: " + filename);
            }
        }
        return scores;
    }
}

