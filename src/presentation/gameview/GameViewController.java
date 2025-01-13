package presentation.gameview;

import application.Main;
import business.Player;
import business.RandomZahlenManager;
import business.ScoreManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import presentation.Scenes;
import presentation.ViewController;

/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 Die GameViewControllerKlasse ist das Herzstück unseres Projektes. Hier befindet sich nämlich
 alle Funktionen rund um Pianotiles. Das heißt hier wird das Spiel zum Laufen gebracht
 und alle Events gehandelt. Zuddem wird eine neue Stage erstellt die für Pause und Play zuständig ist.
 **/
public class GameViewController extends ViewController<Main> {

    private final GameView gameView;
    private Timeline noteTimeline;
    private boolean gameOver;
    private final Player player1;
    private final Player player2;
    public static Label scoreLabel;
    public static int score = 0;
    private final Button playPause;


    public GameViewController(Main application, Player player1,Player player2) {
        super(application);
        rootView = new GameView();
        gameView = (GameView) rootView;
        this.player1 = player1;
        this.player2 = player2;
        scoreLabel= gameView.scoreLabel;
        playPause=gameView.pauseButton;
        initialize();
    }

    @Override
    public void initialize() {
        setupNoteRectangleHandlers();
        startGame();
        playPause.addEventHandler(ActionEvent.ACTION,
                event -> {
                    player1.pause();
                    System.out.println("pause");
                    GameViewController.this.stopGame();
                    GameViewController.this.popupPause();

                }
        );
    }

    private void setupNoteRectangleHandlers() {
        Rectangle[][] noteRectangles = gameView.getNoteRectangles();
        for (int row = 0; row < noteRectangles.length; row++) {
            for (int col = 0; col < noteRectangles[row].length; col++) {
                Rectangle noteRectangle = noteRectangles[row][col];
                noteRectangle.setOnMouseClicked(event -> {
                    if (!gameOver) {
                        handleNoteClick(noteRectangle);
                    }
                });
            }

        }
    }


    public void startGame() {
        if(RandomZahlenManager.counterProperty().get()<=2){
            score=0;
        }
        float tempo = player1.getTempo("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/ETA_GameView.mp3");
        double noteDuration= 60.0/tempo;

        noteTimeline = new Timeline(new KeyFrame(Duration.seconds(noteDuration), event -> {
            if (!gameOver) {
                moveNotes();
            }

        }));

        noteTimeline.setCycleCount(Timeline.INDEFINITE);
        noteTimeline.play();
    }


    private void stopGame() {
        if (noteTimeline != null) {
            noteTimeline.pause();
        }
    }

    private void moveNotes() {
        Rectangle[][] noteRectangles = gameView.getNoteRectangles();
        for (int row = noteRectangles.length - 1; row > 0; row--) {
            for (int col = 0; col < noteRectangles[row].length; col++) {
                noteRectangles[row][col].setFill(noteRectangles[row - 1][col].getFill());
            }
        }
        for (int col = 0; col < noteRectangles[0].length; col++) {
            noteRectangles[0][col].setFill(Color.rgb(255, 255, 255, 0));
        }
        int colForBlackNote = (int) (Math.random() * 4);
        noteRectangles[0][colForBlackNote].setFill(Color.BLACK);
        System.out.println("black"+colForBlackNote);

        int random = (int) (Math.random() * 3);

        if (random == 2) {
            int colForPinkNote = (int) (Math.random() * 4);
            noteRectangles[0][colForPinkNote].setFill(Color.PINK);
            System.out.println("pink"+colForPinkNote);
        }

        for (int col = 0; col < noteRectangles.length; col++) {

            if (noteRectangles[3][col].getFill().equals(Color.BLACK)) {
                System.out.println("Setze auf Maroon...");
                noteRectangles[3][col].setFill(Color.MAROON);
                stopGame();
                RandomZahlenManager.counterErhöhen();

                Timeline delayTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    if(RandomZahlenManager.counterProperty().get()>=2){
                        application.switchScene(Scenes.GAME_OVER);
                        player1.stopMusik();
                        player1.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/GameOver.mp3");

                    }else {
                        application.switchScene(Scenes.MINIGAMELOESEN_VIEW);
                        score= ScoreManager.currentScoreProperty().intValue();
                        player1.stopMusik();
                        player1.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/MinispielMusic.mp3");
                    }
                }));
                delayTimeline.setCycleCount(1);
                delayTimeline.play();
            }
        }
    }

    private void handleNoteClick(Rectangle noteRectangle) {

        if (noteRectangle.getFill() == Color.BLACK) {
            noteRectangle.setFill(Color.rgb(255, 255, 255, 0));
            updateScore();
        }
        else if (noteRectangle.getFill() == Color.PINK) {
            noteRectangle.setFill(Color.rgb(255, 255, 255, 0));
            player2.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/mjau3-82957.mp3");
            playPause.setOnAction(e ->{
                player2.stopMusik();
            });
            updateScore();
            updateScore();
            updateScore();
        }
        else if (noteRectangle.getFill() == Color.MAROON) {
            if(RandomZahlenManager.counterProperty().get()>=2){
                application.switchScene(Scenes.GAME_OVER);
                player1.stopMusik();
                player1.stopMusik();
                    player1.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/GameOver.mp3");

            } else {
                application.switchScene(Scenes.MINIGAMELOESEN_VIEW);
                player1.stopMusik();
                    player1.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/MinispielMusic.mp3");
            }
        }
        else {
            noteRectangle.setFill(Color.rgb(255, 181, 102));

            // Kurze Verzögerung, um die lila Farbe zu zeigen, bevor das Spiel beendet wird
            Timeline delayTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
                gameOver = true;
                application.switchScene(Scenes.GAME_OVER);
                player1.stopMusik();
                        player1.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/GameOver.mp3");
            }));
            delayTimeline.setCycleCount(1);
            delayTimeline.play();
        }
    }

    private void updateScore() {
        score++;
        scoreLabel.setText("Score: " + score);
        ScoreManager.saveScore(score);

        if (score <= 10) {
            adjustNoteDuration(1);
        } else if (score <= 20) {
            adjustNoteDuration(0.8);
        } else if (score <= 50) {
            adjustNoteDuration(0.6);
        } else if (score <= 80) {
            adjustNoteDuration(0.4);
        } else if (score <= 100) {
            adjustNoteDuration(0.2);
        }
    }
    private void adjustNoteDuration(double newDuration) {
        stopGame();

        noteTimeline = new Timeline(new KeyFrame(Duration.seconds(newDuration), event -> {
            if (!gameOver) {
                moveNotes();
            }
        }));

        noteTimeline.setCycleCount(Timeline.INDEFINITE);
        noteTimeline.play();
    }
    private void popupPause() {
        Label scoreLabel = new Label();
        scoreLabel.setStyle("-fx-font-family: 'Lucky Coin';-fx-text-fill:#5C833D;-fx-font-size: 20px;");
        scoreLabel.textProperty().bind(ScoreManager.currentScoreProperty().asString("Score: %d"));
        Stage popupStage = new Stage();
        StackPane popupRoot = new StackPane();
        Button playButton = new Button("play");
        playButton.setStyle("-fx-font-family: 'Lucky Coin';-fx-text-fill:#5C833D; -fx-font-size: 16px;");
        Button retryButton= new Button("Retry");
        retryButton.setStyle("-fx-font-family: 'Lucky Coin';-fx-text-fill:#5C833D; -fx-font-size: 16px;");
        popupRoot.setStyle("-fx-background-color:#ffb566");

        playButton.setOnAction(e ->{
            player1.wiedergabeAngestoßen();
            popupStage.close();
            resumeGame();
        });

        retryButton.setOnAction(e ->{
            RandomZahlenManager.counterAufNull();
            ScoreManager.resetScore();
            popupStage.close();
            application.switchScene(Scenes.START_VIEW);
            player1.stopMusik();
                player1.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/Startview.mp3");

        });

        VBox alles=new VBox(scoreLabel,playButton,retryButton);
        scoreLabel.setPadding(new Insets(20));
        scoreLabel.setAlignment(Pos.TOP_CENTER);
        alles.setAlignment(Pos.CENTER);
        alles.setSpacing(50);
        popupRoot.getChildren().addAll(scoreLabel,alles);
        Scene popupScene = new Scene(popupRoot, 300, 250);
        popupStage.setScene(popupScene);
        popupStage.initModality(Modality.NONE);
        popupStage.show();
    }
    public void resumeGame() {
        if (noteTimeline != null) {
            noteTimeline.play();
        } else {
            startGame();
        }
    }


}