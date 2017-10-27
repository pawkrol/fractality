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
import org.lwjgl.system.Configuration;

public class AppCreator implements EventObserver {

    private Game game;
    private LeeuwenbergTree leeuwenbergTree;

    public void setup() {
        Configuration.STACK_SIZE.set(2048);
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
        Scene scene = new Scene();
        Transform baseTransform = new Transform();

        scene.getScenegraph()
                .setRoot(baseTransform);

        leeuwenbergTree = new LeeuwenbergTree(scene);

        game.setScene(scene);
        game.start();
    }

    @Override
    public void receiveEvent(int event, Object... params) {
        if (event == Event.MOUSE_BUTTON_RELEASED) {
            leeuwenbergTree.evolve();
        }
    }
}
