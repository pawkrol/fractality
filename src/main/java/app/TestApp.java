package app;

import engine.component.Component;
import engine.component.Transform;
import engine.core.Game;
import engine.scene.GameObject;
import engine.scene.Scene;

public class TestApp {

    public static void main(String[] args) {
        Game game = new Game();
        game.createWindow(1200, 800, "fractality", false);

        Scene scene = new Scene();
        GameObject gameObject = new GameObject(scene);
        gameObject.addComponent(Component.Type.TRANSFORM, new Transform());

        scene.getScenegraph().setRoot(gameObject);

        game.setScene(scene);
        game.start();
    }

}
