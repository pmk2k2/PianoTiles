package presentation;

import application.Main;
import business.Player;
import business.ScoreManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameViewController extends ViewController<Main> {

    private Stage primarystage;
    private GameView gameView;
    private Timeline noteTimeline;
    private boolean gameOver;
    private Player player;
    public static Label scoreLabel;
    protected static int score=0;
    private Button playPause;

    public GameViewController(Main application, Player player,Stage primaryStage) {
        super(application);
        rootView = new GameView();
        gameView = (GameView) rootView;
        this.player = player;
        this.scoreLabel= gameView.scoreLabel;
        this.score= gameView.score;
        this.primarystage=primaryStage;
        playPause=gameView.pauseButton;
        initialize();
    }

    @Override

    public void initialize() {
        setupNoteRectangleHandlers();
        startGame();
        playPause.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });
        playPause.addEventHandler(ActionEvent.ACTION,
                event -> {
                    player.pause();
                    System.out.println("pause");
                    stopGame();
                    popupPause();

                }
        );
    }

    private void setupNoteRectangleHandlers() {
        Rectangle[][] noteRectangles = gameView.getNoteRectangles();
        for (int row = 0; row < noteRectangles.length; row++) {
            for (int col = 0; col < noteRectangles[row].length; col++) {
                Rectangle noteRectangle = noteRectangles[row][col];

                noteRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (!gameOver) {
                            handleNoteClick(noteRectangle);
                        }
                    }
                });

                if(row== 3&& noteRectangle.getFill() == Color.BLACK) {
                    noteRectangle.setFill(Color.MAROON);
                }
            }
        }
    }


    public void startGame() {
        float tempo = player.getTempo("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/twinkle-twinkle-little-star-background-childrenx27s-music-30-sec-178225.mp3");
        double noteDuration= 70.0/tempo;

        noteTimeline = new Timeline(new KeyFrame(Duration.seconds(noteDuration), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!gameOver) {
                    moveNotes();
                }

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
            noteRectangles[0][col].setFill(Color.WHITE);
        }

        int colForBlackNote = (int) (Math.random() * 4);
        noteRectangles[0][colForBlackNote].setFill(Color.BLACK);

            for (int col = 0; col < noteRectangles.length; col++) {
                if (noteRectangles[3][col].getFill().equals(Color.BLACK)) {
                    noteRectangles[3][col].setFill(Color.MAROON);
                    stopGame();
                    Timeline delayTimeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                        application.switchScene(Scenes.MINIGAMELOESEN_VIEW);
                    }));
                    delayTimeline.setCycleCount(1);
                    delayTimeline.play();
                }
            }
    }

    private void handleNoteClick(Rectangle noteRectangle) {

        if (noteRectangle.getFill() == Color.BLACK) {
            noteRectangle.setFill(Color.WHITE);
            updateScore();
        } else {
            noteRectangle.setFill(Color.GRAY);
            gameOver = true;
            player.pause();
            application.switchScene(Scenes.GAME_OVER);
        }
    }

    private void updateScore() {
        score++;
        scoreLabel.setText("Score: " + score);
        ScoreManager.saveScore(score);
    }


    private void popupPause() {
        Label scoreLabel = new Label();
        scoreLabel.textProperty().bind(ScoreManager.currentScoreProperty().asString("Score: %d"));
        Stage popupStage = new Stage();
        StackPane popupRoot = new StackPane();
        Button playButton = new Button("play");
        Button retryButton= new Button("Retry");
        retryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                popupStage.close();
                application.switchScene(Scenes.GAME_VIEW);
                player.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/twinkle-twinkle-little-star-background-childrenx27s-music-30-sec-178225.mp3p");
            }
        });

        playButton.setOnAction(e ->{
            player.wiedergabeAngestoßen();
            popupStage.close();
            startGame();
        });

        VBox alles=new VBox(playButton,scoreLabel,retryButton);
        alles.setAlignment(Pos.CENTER);
        alles.setSpacing(30);
        popupRoot.getChildren().addAll(alles,scoreLabel);
        Scene popupScene = new Scene(popupRoot, 300, 250);
        popupStage.setScene(popupScene);
        popupStage.initModality(Modality.NONE);
        popupStage.show();
    }


}