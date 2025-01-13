package presentation.minigame;
import application.Main;
import business.Player;
import business.RandomZahlenManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import presentation.Scenes;
import presentation.ViewController;

/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 Um das Spiel etwas intressanter zu gestalten kann man nur eine Zahl sehen.
 Dabei muss diese aufgedeckt werden, indem man auf die Kasten drauf drückt.
 **/
public class MinispielViewController extends ViewController<Main> {

    private MinispielView minispielView;
    public int a; //katzename1
    public int b; //katzename2
    public int c; //katzename3
    public Button katzebutton1;
    public Button katzebutton2;
    public Button katzebutton3;
    public Label introduction;
    public ImageView sehenbild1;
    public ImageView sehenbild2;
    public ImageView sehenbild3;
    public Button okButton;
    public Player player;
    public Button letztegeklickteButton;
    RandomZahlenManager randomZahlenManager;


    public MinispielViewController (Main application, Player player) {
        super(application);
        rootView = new MinispielView();
        minispielView = (MinispielView) rootView;
        katzebutton1=minispielView.katzebutton1;
        katzebutton2=minispielView.katzebutton2;
        katzebutton3=minispielView.katzebutton3;
        introduction=minispielView.introduction;
        sehenbild1=minispielView.sehenbild1;
        sehenbild2=minispielView.sehenbild2;
        sehenbild3=minispielView.sehenbild3;
        okButton=minispielView.ok;
        randomZahlenManager = new RandomZahlenManager();
        this.player=player;
        initialize();
    }
    public void initialize() {
        a = RandomZahlenManager.randomZahlenPropertyA().get();
        b = RandomZahlenManager.randomZahlenPropertyB().get();
        c = RandomZahlenManager.randomZahlenPropertyC().get();

        katzebutton1.setOnAction(event -> buttonszahlzeigen(katzebutton1, a));
        katzebutton2.setOnAction(event -> buttonszahlzeigen(katzebutton2, b));
        katzebutton3.setOnAction(event -> buttonszahlzeigen(katzebutton3, c));

        okButton.setOnAction(event -> {
            application.switchScene(Scenes.GAME_VIEW);
            player.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/ETA_GameView.mp3");
            player.stopMusik();
        });
    }

    private void buttonszahlzeigen(Button button, int zahl) {
        if (letztegeklickteButton != null) {
            letztegeklickteButton.setText("");
        }
        button.setText(" " + zahl + " ");
        letztegeklickteButton = button;
    }

}