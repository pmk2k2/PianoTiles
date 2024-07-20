package presentation;

import application.Main;
import business.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.io.IOException;

public class StartViewController extends ViewController<Main> {
    @FXML
    private Button startButton;
    @FXML
    private Button howToPlayButton;
    @FXML
    private Slider volumeSlider;

    private Player player;

    public StartViewController(Main application, Player player) {
        super(application);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartView.fxml"));
            loader.setController(this);
            rootView = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.player = player;
        initialize();
    }

    @Override
    public void initialize() {
        startButton.setOnAction(event -> {
            application.switchScene(Scenes.MINIGAME_VIEW);
        });

        howToPlayButton.setOnAction(event -> application.switchScene(Scenes.HOW_TO_PLAY_VIEW));
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                player.volume(volumeSlider.getValue());
            }
        });
    }
}
