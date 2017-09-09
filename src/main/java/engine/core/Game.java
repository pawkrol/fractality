package engine.core;

import engine.message.Message;
import engine.message.MessageBus;
import engine.message.MessageObserver;
import engine.render.Renderer;
import engine.scene.Scene;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class Game implements MessageObserver{

    private Engine engine;

    public Game() {
        this.engine = new Engine();

        MessageBus messageBus = MessageBus.getInstance();
        messageBus.attach(this);
    }

    public void createWindow(int width, int height, String title, boolean resizable) {
        engine.createWindow(width, height, title, resizable);
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
    public void receiveMessage(int message, Object... params) {
        if (message == Message.KEY_RELEASED) {
            if ( (int) params[0] == GLFW_KEY_ESCAPE) {
                engine.closeWindow();
            }
        }
    }
}
