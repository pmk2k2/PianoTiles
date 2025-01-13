package business;

import javafx.beans.property.SimpleIntegerProperty;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 Die Klasse ScoreManager beschäftigt sich im allgemeinen mit dem Score.
 Dabei befinden sich hier Methoden um z.b. den Score zu aktualisieren oder zu reseten.
 Zudem werden alle Scores in einer Textdatei abgespeichert, um den Highscore zu bestimmen.
 **/

public class ScoreManager {
    private static final String SCORE_FILE_PATH = "/Users/macbook/IdeaProjects/pianotilesdemo copy/src/business/scores.txt";
    private static final SimpleIntegerProperty currentScore = new SimpleIntegerProperty();

    public static SimpleIntegerProperty currentScoreProperty() {
        return currentScore;
    }

    public static void saveScore(int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCORE_FILE_PATH, true))) {
            writer.println(score);
            writer.flush();
            currentScore.set(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getAllScores() {
        List<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    scores.add(Integer.parseInt(line));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return scores;
    }
    public static void resetScore() {
        currentScore.set(0);
    }

    public static int getHighestScore() {
        List<Integer> allScores = getAllScores();
        if (!allScores.isEmpty()) {
            int highestScore= allScores.stream().max(Integer::compareTo).orElse(0);
            return Math.max(highestScore, currentScore.intValue());
        }
        return 0;
    }
}
