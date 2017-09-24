package app;

import engine.core.Game;
import engine.render.Camera;
import modules.EmptyRenderConfig;
import engine.render.Renderer;
import engine.scene.GameObject;
import engine.scene.Scene;
import engine.scene.Transform;
import modules.ClearFrame;

public class TestApp {

    public static void main(String[] args) {
        Game game = new Game();
        game.createWindow(1200, 800, "fractality", false);

        Renderer renderer = new Renderer(new Camera(), new ClearFrame(), new EmptyRenderConfig());

        TestShaderProgram testShaderProgram = new TestShaderProgram(1200, 800);
        testShaderProgram.create();

        Scene scene = new Scene();

        Transform transform = new Transform();
        transform.getTranslation().sub(.7f, 0, 2);
        transform.getRotation().rotate(0, 0, (float) Math.toRadians(5));

        Transform transform2 = new Transform();
        transform2.getTranslation().add(1.4f, 0, 0);
//        transform2.getRotation().rotate(0, 0, (float) Math.toRadians(15));

        GameObject gameObject = new Cube(scene, testShaderProgram);
        GameObject gameObject2 = new Cube(scene, testShaderProgram);

        scene.getScenegraph().setRoot(transform);
        scene.getScenegraph().getRoot().addChildren(gameObject);
        scene.getScenegraph().getRoot().addChildren(transform2).addChildren(gameObject2);

        game.setScene(scene);
        game.setRenderer(renderer);
        game.start();
    }

}
