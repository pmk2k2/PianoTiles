package application;

import java.util.HashMap;
import business.Player;
import business.RandomZahlenManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.gameover.GameOverController;
import presentation.gameview.GameViewController;
import presentation.howtoplay.HowToPlayController;
import presentation.minigame.MinispielLoesenViewController;
import presentation.minigame.MinispielViewController;
import presentation.Scenes;
import presentation.startview.StartViewController;
import presentation.ViewController;
/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 In der Main erstellen wir jeweils die Szenen für unser Pianotile spiel, Minispiel.
 Dabei werden alle Szenen immer dann erstellt wenn man diese braucht.
 **/
public class Main extends Application {
    private HashMap<Scenes, Pane> scenes;
    private Scene scene;
    private Pane currentScene;
    private Player player;
    private Player player2;
    private MinispielViewController mini = null;
    private RandomZahlenManager randomNumber;

    @Override
    public void init() {
        player= new Player();
        player2= new Player();
        randomNumber = new RandomZahlenManager();
        scenes = new HashMap<>();
    }
    @Override
    public void start(Stage primaryStage) {
        ViewController<Main> controller = new StartViewController(this, player);
        scenes.put(Scenes.START_VIEW, controller.getRootView());
        player.play("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/Startview.mp3");
        currentScene = scenes.get(Scenes.START_VIEW);

        primaryStage.setTitle("Piano Tiles Game");
        scene = new Scene(currentScene, 400, 700);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
        randomNumber.randomWerte();

    }


    public void switchScene(Scenes sceneName) {
        Stage primaryStage = null;
        Pane nextScene;
        ViewController<Main> controller;

        switch(sceneName) {
            case START_VIEW:
                controller = new StartViewController(this, player);
                scenes.put(Scenes.START_VIEW, controller.getRootView());
                break;

            case GAME_VIEW:
                controller = new GameViewController(this, player,player2);
                scenes.put(Scenes.GAME_VIEW, controller.getRootView());
                break;

            case MINIGAME_VIEW:
                controller = new MinispielViewController(this, player);
                scenes.put(Scenes.MINIGAME_VIEW, controller.getRootView());
                mini = (MinispielViewController) controller;
                break;

            case MINIGAMELOESEN_VIEW:
                MinispielViewController mini = new MinispielViewController(this, player);
                controller = new MinispielLoesenViewController(this, player, mini);
                scenes.put(Scenes.MINIGAMELOESEN_VIEW, controller.getRootView());
                break;

            case HOW_TO_PLAY_VIEW:
                controller = new HowToPlayController(this);
                scenes.put(Scenes.HOW_TO_PLAY_VIEW, controller.getRootView());
                break;

            case GAME_OVER:
                controller = new GameOverController(this, player);
                scenes.put(Scenes.GAME_OVER, controller.getRootView());
                break;


            default:
                controller = new StartViewController(this, player);
                scenes.put(Scenes.START_VIEW, controller.getRootView());
                break;
        }


        if (scenes.containsKey(sceneName)) {
            nextScene = scenes.get(sceneName);
            scene.setRoot(nextScene);
            currentScene = nextScene;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}