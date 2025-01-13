package presentation.howtoplay;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import presentation.Scenes;
import presentation.ViewController;

import java.io.IOException;
/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 In der HowToPlay wird ebenfalls mit einer FXML-Datei gearbeitet. Die Klasse
 ist eine Anleitung zum PianoTilesSpiel.
 **/

public class HowToPlayController extends ViewController<Main> {
    @FXML
    private Button backButton;
    public HowToPlayController(Main application) {
        super(application);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HowToPlay.fxml"));
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
