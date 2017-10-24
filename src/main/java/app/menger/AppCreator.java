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
import modules.configs.EnableBlending;
import modules.configs.EnableCulling;
import org.lwjgl.system.Configuration;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_C;

public class AppCreator implements EventObserver {

    private Game game;
    private MengerSponge mengerSponge;

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
        MengerShader mengerShader = new MengerShader(1200, 800);
        mengerShader.create();

        Scene scene = new Scene();
        Box box = new Box(scene, mengerShader);

        Transform boxTransform = new Transform();
//        boxTransform.getScale().div(2f);

        scene.getScenegraph()
                .setRoot(boxTransform)
                .addChildren(box);

        mengerSponge = new MengerSponge(box);

        game.setScene(scene);
        game.start();
    }

    @Override
    public void receiveEvent(int event, Object... params) {
        if (event == Event.MOUSE_BUTTON_RELEASED) {
            mengerSponge.evolve();
            mengerSponge.addSelfToScene(game.getScene());
        }
    }

    private void resetScene() {
        game.getScene()
                .getScenegraph()
                .clear();

        Transform boxTransform = new Transform();

        game.getScene()
                .getScenegraph()
                .setRoot(boxTransform);
    }
}
