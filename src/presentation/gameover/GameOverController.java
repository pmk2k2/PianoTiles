package presentation.gameover;

import application.Main;
import business.Player;
import business.RandomZahlenManager;
import business.ScoreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.Scenes;
import presentation.ViewController;
import presentation.gameview.GameViewController;
/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 Die UI-Komponente dieser Klasse werden mit einer FXMl-Datei erstellt. Zudem wird der aktuelle(gespielte)
 Score mit den Highscore verglichen.
 **/

import java.io.IOException;

public class GameOverController extends ViewController<Main> {
    @FXML
    private Label scoreLabel;
    @FXML
    private Label highScoreLabel;
    @FXML
    private Button replayButton;
    private final Player player;

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
        replayButton.setOnAction(event ->{
            player.stopMusik();
            player.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/Startview.mp3");
            GameViewController.score=0;
            RandomZahlenManager.counterAufNull();
            application.switchScene(Scenes.START_VIEW);
            RandomZahlenManager.randomWerte();
        });
    }
}
