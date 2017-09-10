package app;

import engine.core.Game;
import engine.render.RenderConfig;
import engine.render.Renderer;
import engine.scene.GameObject;
import engine.scene.Scene;
import engine.scene.Transform;
import engine.scene.shader.ShaderProgram;

public class TestApp {

    public static void main(String[] args) {
        Game game = new Game();
        game.createWindow(1200, 800, "fractality", false);

        Scene scene = new Scene();
        Renderer renderer = new Renderer(new RenderConfig() {
            @Override
            public void enable() {

            }

            @Override
            public void disable() {

            }
        });

        ShaderProgram shaderProgram = null;
        try {
            shaderProgram = new ShaderProgram();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GameObject gameObject = new GameObject(scene, shaderProgram);

        scene.getScenegraph().setRoot(new Transform());
        scene.getScenegraph().getRoot().addChildren(gameObject);

        game.setScene(scene);
        game.setRenderer(renderer);
        game.start();
    }

}
