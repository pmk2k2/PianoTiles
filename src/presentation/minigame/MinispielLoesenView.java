package presentation.minigame;
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

/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 Diese Klasse taucht auf wenn ein schwarzes Pianotile die Limitline überschreitet.
 Hier werden die UI-Komponente gestaltet.
 **/
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
        introduction.setFont(Font.font("Lucky Coin", 20));

        merken= new Label("Do you remember the number? (✿◡‿◡)");
        merken.setAlignment(Pos.CENTER);
        merken.setFont(Font.font("Lucky Coin", 16));

        Image bild1 = new Image("assets/Herunterladen.gif");
        Image bild2 = new Image("assets/Herunterladen_1.gif");
        Image bild3 = new Image("assets/oie_10114957TQmmJiB2.gif");

        sehenbild1 = new ImageView(bild1);
        sehenbild1.setFitHeight(100);
        sehenbild1.setFitWidth(100);

        sehenbild2 = new ImageView(bild2);
        sehenbild2.setFitHeight(100);
        sehenbild2.setFitWidth(120);

        sehenbild3 = new ImageView(bild3);
        sehenbild3.setFitHeight(100);
        sehenbild3.setFitWidth(100);

        HBox bilderreihe= new HBox(sehenbild3, sehenbild1, sehenbild2);
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
        ok.setFont(Font.font("Lucky Coin", 16));
        ok.setAlignment(Pos.CENTER);
        ok.setStyle("-fx-background-color:#ffb566 ;-fx-border-color: black; -fx-border-width: 3;");

        VBox alles= new VBox(introduction, merken, bilderreihe,textFields, ok);
        alles.setAlignment(Pos.CENTER);
        alles.setSpacing(60);

        BackgroundFill backgroundFill = new BackgroundFill(Color.PINK, null, null);
        Background background = new Background(backgroundFill);
        alles.setBackground(background);

        this.getChildren().add(alles);
        pane=new BorderPane(alles);

        setCenter(pane);
    }

}