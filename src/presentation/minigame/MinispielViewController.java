package presentation;
import application.Main;
import business.Player;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MinispielViewController extends ViewController<Main> {

    private MinispielView minispielView;
    SimpleIntegerProperty a; //katzename1
    SimpleIntegerProperty b; //katzename2
    SimpleIntegerProperty c; //katzename3
    private Button katzebutton1;
    private Button katzebutton2;
    private Button katzebutton3;
    private Label introduction;
    private ImageView sehenbild1;
    private ImageView sehenbild2;
    private ImageView sehenbild3;
    private Button okButton;
    private Player player;
    private Button letztegeklickteButton;


    public MinispielViewController (Main application, Player player) {
        super(application);
        rootView = new MinispielView();  // Zuerst minispielView initialisieren
        minispielView = (MinispielView) rootView;
        katzebutton1=minispielView.katzebutton1;
        katzebutton2=minispielView.katzebutton2;
        katzebutton3=minispielView.katzebutton3;
        introduction=minispielView.introduction;
        sehenbild1=minispielView.sehenbild1;
        sehenbild2=minispielView.sehenbild2;
        sehenbild3=minispielView.sehenbild3;
        okButton=minispielView.ok;
        a = new SimpleIntegerProperty();
        b = new SimpleIntegerProperty();
        c = new SimpleIntegerProperty();
        this.player=player;
        initialize();
    }
    public void initialize() {
        aProperty().setValue((int) (Math.random() * 10));
        bProperty().setValue((int) (Math.random() * 10));
        cProperty().setValue((int) (Math.random() * 10));
        System.out.println(a);System.out.println(b); System.out.println(c);

        katzebutton1.setOnAction(event -> buttonszahlzeigen(katzebutton1, getA()));
        katzebutton2.setOnAction(event -> buttonszahlzeigen(katzebutton2, getB()));
        katzebutton3.setOnAction(event -> buttonszahlzeigen(katzebutton3, getC()));
        okButton.setOnAction(event -> {
            // Switch scene when the "Start Game" button is clicken
            System.out.println("GEHT REIN");
            application.switchScene(Scenes.GAME_VIEW);
            player.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/twinkle-twinkle-little-star-background-childrenx27s-music-30-sec-178225.mp3");
            System.out.println("spielt");
        });
    }
    private void buttonszahlzeigen(Button button, int zahl) {
        if (letztegeklickteButton != null) {
            letztegeklickteButton.setText(""); // Zahl im vorherigen Button unsichtbar machen
        }

        button.setText(" " + zahl + " "); // // Zahl im aktuellen Button sichtbar machen

        letztegeklickteButton = button; // // Aktuellen Button als zuletzt geklickten Button speichern
    }

//    private void minispiel1() {
//        // Initialisiere die Werte von a, b und c
//        aProperty().setValue((int) (Math.random() * 10));
//        bProperty().setValue((int) (Math.random() * 10));
//        cProperty().setValue((int) (Math.random() * 10));
//
//
//        // Überprüfe, ob a, b und c eindeutig sind und gegebenenfalls neu generiert werden
////        if (this.a == this.b && this.a == this.c) {
////     	   this.a = (int) (Math.random() * 10);
////        }else if (this.b == this.a && this.b == c) {
////     	   this.b = (int) (Math.random() * 10);
////        } else if (this.c == this.a && this.c == this.b) {
////     	   this.c = (int) (Math.random() * 10);
////
////        }
//        System.out.println(a);System.out.println(b); System.out.println(c);
//    }
    public SimpleIntegerProperty aProperty() {
        return a;
    }
    public SimpleIntegerProperty bProperty() {
        return b;
    }
    public SimpleIntegerProperty cProperty() {
        return c;
    }

    public final int getA() {
        return a.get();
    }

    public final int getB() {
        return b.get();
    }

    public final int getC() {
        return c.get();
    }
}