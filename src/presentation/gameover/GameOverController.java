package presentation;

import application.Main;
import business.Player;
import business.ScoreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameOverController extends ViewController<Main> {
    @FXML
    private Label gameOverLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label highScoreLabel;
    @FXML
    private Button replayButton;
    private Player player;

    public GameOverController(Main application, Player player) {
        super(application);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverView.fxml"));
            loader.setController(this);
            rootView = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.player= player;
        initialize();
    }

    @Override
    public void initialize() {
        scoreLabel.textProperty().bind(ScoreManager.currentScoreProperty().asString("Score: %d"));
        highScoreLabel.setText("Highest Score: "+ ScoreManager.getHighestScore());
        replayButton.setOnAction(event ->{ application.switchScene(Scenes.GAME_VIEW);
            player.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/twinkle-twinkle-little-star-background-childrenx27s-music-30-sec-178225.mp3");
        });
    }
}
