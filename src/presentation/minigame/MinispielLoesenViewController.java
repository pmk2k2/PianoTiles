package presentation;

import application.Main;
import business.Player;
import business.ScoreManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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



    public MinispielLoesenViewController (Main application, Player player,MinispielViewController minispielAntwort) {
        super(application);
        rootView = new MinispielLoesenView();  // Zuerst minispielView initialisieren
        minispielView = (MinispielLoesenView) rootView;
        textField1=minispielView.textField1;
        textField2=minispielView.textField2;
        textField3=minispielView.textField3;
        okButton=minispielView.ok;
        this.player=player;
        this.minispielAntwort = minispielAntwort;
        this.a = minispielAntwort.getA();
        this.b = minispielAntwort.getB();
        this.c = minispielAntwort.getC();
        initialize();
    }

    public void initialize() {
        okButton.setOnAction(event -> {
            int input1 = Integer.parseInt(textField1.getText());
            int input2 = Integer.parseInt(textField2.getText());
            int input3 = Integer.parseInt(textField3.getText());

            if(input1==a && input2==b && input3==c) {
                application.switchScene(Scenes.GAME_VIEW);
                GameViewController.score = ScoreManager.currentScoreProperty().get();
                GameViewController.scoreLabel.setText("Score: "+ ScoreManager.currentScoreProperty().get());
            }else {
                System.out.println("GAMEOVER");
            }
        });

    }}