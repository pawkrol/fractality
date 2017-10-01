package app.menger;

import engine.core.Game;
import engine.render.Camera;
import engine.render.Renderer;
import engine.scene.Scene;
import engine.scene.Transform;
import modules.ClearFrame;
import modules.DefaultDrawCallConfig;
import modules.EnableCulling;

public class MengerSponge {

    private static Game setup() {
        Game game = new Game();
        game.createWindow(1200, 800, "Menger Sponge", false);

        Renderer renderer = new Renderer.RendererBuilder()
                .drawCallConfig(new DefaultDrawCallConfig())
                .renderConfig(new EnableCulling())
                .frameConfig(new ClearFrame())
                .camera(new Camera())
                .build();
        game.setRenderer(renderer);

        return game;
    }

    public static void main(String[] args) {
        Game game = setup();
        MengerShader mengerShader = new MengerShader(1200, 800);
        mengerShader.create();

        Scene scene = new Scene();
        Transform boxTransform = new Transform();
        Box box = new Box(scene, mengerShader);

        scene.getScenegraph()
                .setRoot(boxTransform)
                .addChildren(box);

        game.setScene(scene);
        game.start();
    }

}
