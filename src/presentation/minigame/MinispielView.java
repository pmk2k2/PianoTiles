package presentation;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MinispielView extends BorderPane {
    public BorderPane pane;
    public Button katzebutton1;
    public Button katzebutton2;
    public Button katzebutton3;
    public Label introduction;
    public Label merken;
    public ImageView sehenbild1;
    public ImageView sehenbild2;
    public ImageView sehenbild3;
    public Button ok;

    public MinispielView() {
        introduction = new Label("Minigame");
        introduction.setAlignment(Pos.TOP_CENTER);
        introduction.setFont(Font.font("Comic Sans MS", 20));

        merken= new Label(" Remember: ");
        merken.setAlignment(Pos.CENTER);
        merken.setFont(Font.font("Comic Sans MS", 16));

        // Bilder laden
        Image bild1 = new Image("assets/angrycat.jpg");
        Image bild2 = new Image("assets/bored_cat.jpg");
        Image bild3 = new Image("assets/happycat.jpg");

        sehenbild1 = new ImageView(bild1);
        sehenbild1.setFitHeight(100);
        sehenbild1.setFitWidth(100);

        sehenbild2 = new ImageView(bild2);
        sehenbild2.setFitHeight(100);
        sehenbild2.setFitWidth(120);

        sehenbild3 = new ImageView(bild3);
        sehenbild3.setFitHeight(100);
        sehenbild3.setFitWidth(100);
        System.out.println("Bild 1 geladen: " + bild1.isError());
        System.out.println("Bild 2 geladen: " + bild2.isError());
        System.out.println("Bild 3 geladen: " + bild3.isError());

        HBox bilderreihe= new HBox(sehenbild1, sehenbild2, sehenbild3);
        bilderreihe.setAlignment(Pos.CENTER);
        bilderreihe.setSpacing(20);

        katzebutton1 = new Button();
        katzebutton1.setPrefSize(50,50);
        katzebutton1.setStyle("-fx-background-color: #F08080;-fx-border-color: black; -fx-border-width: 3;"); // LightCoral


        katzebutton2 = new Button();
        katzebutton2.setPrefSize(50,50);
        katzebutton2.setStyle("-fx-background-color: #F08080;-fx-border-color: black; -fx-border-width: 3;"); // LightCoral


        katzebutton3 = new Button();
        katzebutton3.setPrefSize(50,50);
        katzebutton3.setStyle("-fx-background-color: #F08080;-fx-border-color: black; -fx-border-width: 3;"); // LightCoral


        // Layout erstellen und Buttons hinzufÃ¼gen
        HBox buttons = new HBox(katzebutton1, katzebutton2, katzebutton3); // Horizontal Box
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(80);

        ok= new Button("OK");
        ok.setAlignment(Pos.CENTER);
        ok.setPrefSize(80,30);
        ok.setFont(Font.font("Comic Sans MS", 15));
        ok.setStyle("-fx-background-color: lightgreen;-fx-border-color: black; -fx-border-width: 3;");

        VBox ganzespiel= new VBox(introduction,merken, bilderreihe, buttons,ok);
        ganzespiel.setAlignment(Pos.CENTER);
        ganzespiel.setSpacing(60);

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTYELLOW, null, null);
        Background background = new Background(backgroundFill);
        ganzespiel.setBackground(background);

        this.getChildren().add(ganzespiel);
        pane=new BorderPane(ganzespiel);
//        pane.getStyleClass().add("playerClass");

        setCenter(pane);
    }
}
