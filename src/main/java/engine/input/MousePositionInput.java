package engine.input;

import engine.event.Event;
import engine.event.EventBus;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class MousePositionInput implements GLFWCursorPosCallbackI{

    private static MousePositionInput instance;

    private final EventBus eventBus = EventBus.getInstance();

    private MousePositionInput() {}

    @Override
    public void invoke(long window, double xpos, double ypos) {
        eventBus.propagate(Event.CURSOR_MOVED, xpos, ypos);
    }

    public static MousePositionInput getInstance() {
        if (instance == null) {
            instance = new MousePositionInput();
        }

        return instance;
    }
}
