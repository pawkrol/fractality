package engine.core;

import engine.event.Event;
import engine.event.EventBus;
import engine.event.EventObserver;
import engine.core.render.Renderer;
import engine.scene.Scene;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class Game implements EventObserver {

    private Engine engine;

    public Game() {
        this.engine = new Engine();

        EventBus eventBus = EventBus.getInstance();
        eventBus.attach(this);
    }

    public Window createWindow(int width, int height, String title, boolean resizable) {
        return engine.createWindow(width, height, title, resizable);
    }

    public void start() {
        engine.run();
    }

    public Scene getScene() {
        return engine.getRenderManager().getScene();
    }

    public void setScene(Scene scene) {
        engine.getRenderManager().setScene(scene);
    }

    public Renderer getRenderer() {
        return engine.getRenderManager().getRenderer();
    }

    public void setRenderer(Renderer renderer) {
        engine.getRenderManager().setRenderer(renderer);
    }

    @Override
    public void receiveEvent(int message, Object... params) {
        if (message == Event.KEY_RELEASED) {
            if ( (int) params[0] == GLFW_KEY_ESCAPE) {
                engine.closeWindow();
            }
        }
    }
}
