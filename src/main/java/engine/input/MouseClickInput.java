package engine.input;

import engine.event.Event;
import engine.event.EventBus;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseClickInput implements GLFWMouseButtonCallbackI{

    private static MouseClickInput instance;

    private final EventBus eventBus = EventBus.getInstance();

    private MouseClickInput() {}

    @Override
    public void invoke(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            eventBus.propagate(Event.MOUSE_BUTTON_PRESSED, button);
        } else if (action == GLFW_RELEASE) {
            eventBus.propagate(Event.MOUSE_BUTTON_RELEASED, button);
        }
    }

    public static MouseClickInput getInstance() {
        if (instance == null) {
            instance = new MouseClickInput();
        }

        return instance;
    }
}
