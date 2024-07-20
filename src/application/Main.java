package presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.StartView;

import java.util.HashMap;

public class Main extends Application {
    private HashMap<Scenes, Pane> scenes;
    private Scene scene;
    private Pane currentScene;


    @Override
    public void init() {
        scenes = new HashMap<>();
    }

    @Override
    public void start(Stage primaryStage) {
        ViewController<Main> controller;
        primaryStage.setTitle("Piano Tiles Game");
        controller = new StartView(this);
        scenes.put(Scenes.START_VIEW,  controller.getRootView());

        controller = new GameView(this);
        scenes.put(Scenes.GAME_VIEW,  controller.getRootView());

        Pane root = scenes.get(Scenes.START_VIEW);



        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void switchScene(Scenes sceneName) {
        Pane nextScene;

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
