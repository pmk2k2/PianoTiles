package business;

import java.io.*;
import java.nio.file.*;

public class ScoreManager {
    static int currentscore;
    private static final String SCORE_FILE_PATH = "/Users/macbook/IdeaProjects/pianotilesdemo copy/src/business/scores.txt";

    public static void saveScore(int score) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SCORE_FILE_PATH))) {
            writer.write(String.valueOf(score));
            System.out.println(score);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int getHighScore() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(SCORE_FILE_PATH)));
            return Integer.parseInt(content.trim());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

}
