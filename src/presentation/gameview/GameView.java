package presentation.gameview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 Hier befinden sich die UI-Komponente.
 **/
public class GameView extends Pane {

    private GridPane gridPane;


    public Rectangle[][] noteRectangles;
    public Label scoreLabel;
    public Line limitline;
    public Button pauseButton;

    public GameView() {
        // Hintergrundbild
        Image backgroundImage = new Image("/assets/GameView_BG.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1000);
        backgroundImageView.setFitHeight(800);

        // Initialisierung der GridPane
        gridPane = new GridPane();
        noteRectangles = new Rectangle[4][4];

        // Hinzufügen von BackgroundImageView und GridPane zur GameView
        this.getChildren().addAll(backgroundImageView, gridPane);

        // Weitere Initialisierung und Hinzufügen von UI-Elementen zur GridPane
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Rectangle noteRectangle = new Rectangle(100, 180);
                noteRectangle.setArcWidth(20); // Abrundung der Ecken
                noteRectangle.setArcHeight(20);
                noteRectangle.setFill(Color.TRANSPARENT);
                noteRectangles[row][col] = noteRectangle;
                gridPane.add(noteRectangle, col, row);
            }
        }

        scoreLabel = new Label("Score: " + 0);
        scoreLabel.setStyle("-fx-font-size: 20px;");
        scoreLabel.setPadding(new Insets(5));

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
        Background background = new Background(backgroundFill);
        scoreLabel.setBackground(background);
        GridPane.setConstraints(scoreLabel, 0, 0);

        limitline = new Line(100, 100, 500, 100);
        limitline.setStroke(Color.GREEN);
        limitline.setStrokeWidth(7);
        limitline.prefWidth(200);
        limitline.prefHeight(20);
        GridPane.setValignment(limitline, VPos.TOP);
        GridPane.setConstraints(limitline, 0, 3);
        GridPane.setColumnSpan(limitline, 4);

        pauseButton = new Button();
        pauseButton.setId("pauseButton");
        pauseButton.setAlignment(Pos.TOP_RIGHT);

        HBox alles = new HBox(pauseButton);
        alles.setAlignment(Pos.CENTER);
        GridPane.setConstraints(alles, 3, 0);

        // Hinzufügen von UI-Elementen zur GridPane
        gridPane.getChildren().addAll(scoreLabel, limitline, alles);
    }

    public Rectangle[][] getNoteRectangles() {
        return noteRectangles;
    }
}