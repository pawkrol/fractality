package app.menger;

import engine.core.Game;
import engine.event.Event;
import engine.event.EventBus;
import engine.event.EventObserver;
import engine.render.Camera;
import engine.render.Renderer;
import engine.scene.Scene;
import engine.scene.Transform;
import modules.configs.ClearFrame;
import modules.configs.DefaultDrawCallConfig;
import modules.configs.EnableCulling;

public class AppCreator implements EventObserver {

    private Game game;
    private LeeuwenbergTree leeuwenbergTree;

    public void setup() {
        EventBus.getInstance().attach(this);

        game = new Game();
        game.createWindow(1200, 800, "Menger Sponge", false);

        Renderer renderer = new Renderer.RendererBuilder()
                .drawCallConfig(new DefaultDrawCallConfig())
                .renderConfig(new EnableCulling())
                .frameConfig(new ClearFrame())
                .camera(new Camera())
                .build();
        game.setRenderer(renderer);
    }

    public void start() {
        MengerShader  mengerShader = new MengerShader(1200, 800);
        mengerShader.create();

        Scene scene = new Scene();
        Transform baseTransform = new Transform();

        scene.getScenegraph()
                .setRoot(baseTransform)
                .addChildren(new Plane(scene, mengerShader));

        leeuwenbergTree = new LeeuwenbergTree(scene, mengerShader);

        game.setScene(scene);
        game.start();
    }

    @Override
    public void receiveEvent(int event, Object... params) {
    }
}
