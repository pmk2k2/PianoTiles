package presentation.minigame;

import application.Main;
import business.Player;
import business.RandomZahlenManager;
import business.ScoreManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.Scenes;
import presentation.ViewController;
import presentation.gameview.GameViewController;

/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 Um das spiel fortzusetzen muss natürlich auch die richtige Zahle eingegeben werden. Diese Klasse überprüft,
 ob die eingegebene Zahl mit der angezeigten Zahl übereinstimmt. Wenn dies der Fall ist kann weiter gespielt werden.
 Allerdings gilt dieser Schlüssel(Jocker-leben)nur einmal! Das heißt kommt man erneut über der
 Limitline so verliert man das Spiel
 **/
public class MinispielLoesenViewController extends ViewController<Main> {
    public MinispielLoesenView minispielView;
    TextField textField1;
    TextField textField2;
    TextField textField3;
    public Button okButton;
    Player player;
    int a;
    int b;
    int c;
    MinispielViewController minispielAntwort;
    GameViewController gameView;




    public MinispielLoesenViewController (Main application, Player player,MinispielViewController minispielAntwort) {
        super(application);
        rootView = new MinispielLoesenView();  // Zuerst minispielView initialisieren
        minispielView = (MinispielLoesenView) rootView;
        textField1=minispielView.textField1;
        textField2=minispielView.textField2;
        textField3=minispielView.textField3;
        okButton=minispielView.ok;
        this.player=player;
        System.out.println("MINIGAMEVIEWLOESENCONTROLLER");
        a = RandomZahlenManager.randomZahlenPropertyA().get();
        b = RandomZahlenManager.randomZahlenPropertyB().get();
        c = RandomZahlenManager.randomZahlenPropertyC().get();

        //this.minispielAntwort = minispielAntwort;
        initialize();
    }

    public void initialize() {
        okButton.setOnAction(event -> {
            int input1 = Integer.parseInt(textField1.getText());
            int input2 = Integer.parseInt(textField2.getText());
            int input3 = Integer.parseInt(textField3.getText());

            if(input1==c && input2==a && input3==b) {
                application.switchScene(Scenes.GAME_VIEW);
                player.stopMusik();
                player.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/ETA_GameView.mp3");
                GameViewController.score = ScoreManager.currentScoreProperty().get();
                GameViewController.scoreLabel.setText("Score: "+ ScoreManager.currentScoreProperty().get());
            }else {
                System.out.println("GAMEOVER");
                application.switchScene(Scenes.GAME_OVER);
                player.stopMusik();
                player.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/GameOver.mp3");
            }
        });

    }
}