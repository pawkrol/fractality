package engine.core;

import engine.message.Message;
import engine.message.MessageBus;
import engine.message.MessageObserver;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class Game implements MessageObserver{

    private final MessageBus messageBus;
    private Engine engine;

    public Game() {
        this.messageBus = MessageBus.getInstance();
        this.engine = new Engine();

        messageBus.attach(this);
    }

    public void createWindow(int width, int height, String title, boolean resizable) {
        engine.createWindow(width, height, title, resizable);
    }

    public void start() {
        engine.run();
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
