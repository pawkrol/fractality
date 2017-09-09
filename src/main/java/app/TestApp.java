package app;

import engine.core.Game;
import engine.scene.GameObject;
import engine.scene.Scene;
import engine.scene.Transform;

public class TestApp {

    public static void main(String[] args) {
        Game game = new Game();
        game.createWindow(1200, 800, "fractality", false);

        Scene scene = new Scene();
        GameObject gameObject = new GameObject(scene);

        scene.getScenegraph().setRoot(new Transform());
        scene.getScenegraph().getRoot().addChildren(gameObject);

        game.setScene(scene);
        game.start();
    }

}
