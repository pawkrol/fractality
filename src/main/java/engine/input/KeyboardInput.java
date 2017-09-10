package engine.input;

import engine.event.Event;
import engine.event.EventBus;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardInput implements GLFWKeyCallbackI {

    private static KeyboardInput instance;

    private final EventBus eventBus = EventBus.getInstance();

    private KeyboardInput() {}

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS){
            eventBus.propagate(Event.KEY_PRESSED, key);
        } else if (action == GLFW_RELEASE){
            eventBus.propagate(Event.KEY_RELEASED, key);
        }
    }

    public static KeyboardInput getInstance() {
        if (instance == null) {
            instance = new KeyboardInput();
        }

        return instance;
    }
}
