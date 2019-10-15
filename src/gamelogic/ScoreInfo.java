package gamelogic;

import java.io.Serializable;

/**
 * Class sets score information.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * Constructor sets a name and score.
     *
     * @param name  String.
     * @param score int.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Method returns a name.
     *
     * @return String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method returns a score.
     *
     * @return int.
     */
    public int getScore() {
        return this.score;
    }
}
