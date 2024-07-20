package presentation;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HowToPlayController extends ViewController<Main> {
    @FXML
    private Label titleLabel;
    @FXML
    private Label instruction1Label;
    @FXML
    private Label instruction2Label;
    @FXML
    private Button backButton;
    public HowToPlayController(Main application) {
        super(application);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HowToPlayView.fxml"));
            loader.setController(this);
            rootView = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        initialize();
    }

    @Override
    public void initialize() {
        backButton.setOnAction(event -> application.switchScene(Scenes.START_VIEW));
    }

}
