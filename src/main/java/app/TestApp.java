package app;

import engine.core.Game;
import engine.render.DefaultRenderConfig;
import engine.render.Renderer;
import engine.scene.GameObject;
import engine.scene.Scene;
import engine.scene.Transform;

public class TestApp {

    public static void main(String[] args) {
        Game game = new Game();
        game.createWindow(1200, 800, "fractality", false);

        Renderer renderer = new Renderer(new ClearFrame(), new DefaultRenderConfig());

        Scene scene = new Scene();
        Transform transform = new Transform();
        GameObject gameObject = new Triangle(scene);

        scene.getScenegraph().setRoot(transform);
        scene.getScenegraph().getRoot().addChildren(gameObject);

        game.setScene(scene);
        game.setRenderer(renderer);
        game.start();
    }

}
