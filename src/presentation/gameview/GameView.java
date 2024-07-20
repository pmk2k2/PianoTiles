package presentation;
//Bibiliotheke
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

public class GameView extends Pane {

    private GridPane gridPane;
    public Rectangle[][] noteRectangles;
    public Label scoreLabel;
    public int score = 0; // Score-Variable
    public Line limitline;
    public Button pauseButton;
    public GameView() {
        gridPane = new GridPane();
        noteRectangles = new Rectangle[4][4];

        // Initialize note rectangles in the grid
        //erste for-Svhleife: um die Zeilen des Rasters zu durchlaufen
        for (int row = 0; row < 4; row++) { //row: aktuelle Zeile-> läuft von 0 bis 3, also 4 Zeilen
            //zweite for-Schleife: um die Spalten im Raster zu durchlaufen
            for (int col = 0; col < 4; col++) {//col: aktuelle Spalte-> also auch 4 Salten
                Rectangle noteRectangle = new Rectangle(100, 180, Color.WHITE); // White notes initially
                noteRectangles[row][col] = noteRectangle;

                gridPane.add(noteRectangle, col, row);
            }
        }

        // Initialisiere das Textfeld für den Score
        scoreLabel = new Label("Score: " + score);
        scoreLabel.setStyle("-fx-font-size: 20px;"); // Beispiel: Setze die Schriftgröße
        scoreLabel.setPadding(new Insets(5));

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
        Background background = new Background(backgroundFill);
        scoreLabel.setBackground(background);
        GridPane.setConstraints(scoreLabel, 0, 0); // Setze die Position des Labels oben in der Ecke

        limitline= new Line(100, 100, 500,100);
        limitline.setStroke(Color.BURLYWOOD);
        limitline.setStrokeWidth(7);
        limitline.prefWidth(200);
        limitline.prefHeight(20);
        // Setzt die Position der limitline auf die oberste Position
        GridPane.setValignment(limitline, VPos.TOP);
        GridPane.setConstraints(limitline, 0, 3);
        GridPane.setColumnSpan(limitline, 4 ); // Erstreckt sich

        pauseButton= new Button("play/pause");
        pauseButton.setAlignment(Pos.TOP_RIGHT);

        HBox alles = new HBox(pauseButton);
        alles.setAlignment(Pos.CENTER);
        GridPane.setConstraints(alles, 3, 0);
        this.getChildren().add(gridPane);
        gridPane.getChildren().addAll(scoreLabel,limitline,alles);

    }

    public Rectangle[][] getNoteRectangles() {
        return noteRectangles;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    // Additional methods for updating the game view, handling button clicks, etc.
}