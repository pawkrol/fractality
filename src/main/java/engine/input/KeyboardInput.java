package engine.input;

import engine.message.Message;
import engine.message.MessageBus;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardInput implements GLFWKeyCallbackI {

    private static KeyboardInput instance;

    private final MessageBus messageBus = MessageBus.getInstance();

    private KeyboardInput() {}

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS){
            messageBus.propagate(Message.KEY_PRESSED, key);
        } else if (action == GLFW_RELEASE){
            messageBus.propagate(Message.KEY_RELEASED, key);
        }
    }

    public static KeyboardInput getInstance() {
        if (instance == null) {
            instance = new KeyboardInput();
        }

        return instance;
    }
}
