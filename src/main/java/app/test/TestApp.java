package app.test;

import engine.core.Game;
import engine.render.Camera;
import modules.DefaultDrawCallConfig;
import engine.render.Renderer;
import engine.scene.GameObject;
import engine.scene.Scene;
import engine.scene.Transform;
import modules.ClearFrame;
import modules.EnableCulling;

public class TestApp {

    public static void main(String[] args) {
        Game game = new Game();
        game.createWindow(1200, 800, "fractality", false);

        Renderer renderer = new Renderer.RendererBuilder()
                .drawCallConfig(new DefaultDrawCallConfig())
                .renderConfig(new EnableCulling())
                .frameConfig(new ClearFrame())
                .camera(new Camera())
                .build();

        TestShaderProgram testShaderProgram = new TestShaderProgram(1200, 800);
        testShaderProgram.create();

        Scene scene = new Scene();

        Transform transform = new Transform();
        Transform transform2 = new Transform();
        transform2.getTranslation().add(1f, 0, 0);

        GameObject gameObject = new Cube(scene, testShaderProgram);

        scene.getScenegraph().setRoot(transform);
        scene.getScenegraph().getRoot().addChildren(gameObject);
        scene.getScenegraph().getRoot().addChildren(transform2).addChildren(gameObject);

        game.setScene(scene);
        game.setRenderer(renderer);
        game.start();
    }

}
