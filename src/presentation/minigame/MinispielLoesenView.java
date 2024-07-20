package presentation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MinispielLoesenView extends BorderPane {

    public BorderPane pane;
    public Label introduction;
    public Label merken;
    public ImageView sehenbild1;
    public ImageView sehenbild2;
    public ImageView sehenbild3;
    TextField textField1;
    TextField textField2;
    TextField textField3;
    Button ok;

    public MinispielLoesenView() {
        introduction = new Label("Minigame");
        introduction.setAlignment(Pos.CENTER);
        introduction.setFont(Font.font("Comic Sans MS", 20));

        merken= new Label("Do you Remmber? (✿◡‿◡)");
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

        textField1=new TextField();
        textField1.setPrefSize(30,30);

        textField2=new TextField();
        textField2.setPrefSize(30,30);

        textField3=new TextField();
        textField3.setPrefSize(30,30);

        HBox textFields = new HBox(textField1, textField2, textField3); // Horizontal Box
        textFields.setAlignment(Pos.CENTER);
        textFields.setSpacing(80);

        ok= new Button("OK");
        ok.setFont(Font.font("Comic Sans MS", 16));
        ok.setAlignment(Pos.CENTER);

        VBox alles= new VBox(introduction, merken, bilderreihe,textFields, ok);
        alles.setAlignment(Pos.CENTER);
        alles.setSpacing(60);

        BackgroundFill backgroundFill = new BackgroundFill(Color.HOTPINK, null, null);
        Background background = new Background(backgroundFill);
        alles.setBackground(background);

        this.getChildren().add(alles);
        pane=new BorderPane(alles);

        setCenter(pane);
    }

}