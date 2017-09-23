package app;

import engine.core.Game;
import engine.render.DefaultRenderConfig;
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
        Renderer renderer = new Renderer(new DefaultRenderConfig());

        ShaderProgram shaderProgram = null;
        try {
            shaderProgram = new ShaderProgram();
            shaderProgram.addShader(new TestFragmentShader());
            shaderProgram.addShader(new TestVertexShader());
            shaderProgram.createAndLink();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GameObject gameObject = new Triangle(scene, shaderProgram);

        scene.getScenegraph().setRoot(new Transform());
        scene.getScenegraph().getRoot().addChildren(gameObject);

        game.setScene(scene);
        game.setRenderer(renderer);
        game.start();
    }

}
