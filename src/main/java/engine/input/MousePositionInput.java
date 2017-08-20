package engine.input;

import engine.message.Message;
import engine.message.MessageBus;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class MousePositionInput implements GLFWCursorPosCallbackI{

    private static MousePositionInput instance;

    private final MessageBus messageBus = MessageBus.getInstance();

    private MousePositionInput() {}

    @Override
    public void invoke(long window, double xpos, double ypos) {
        messageBus.propagate(Message.CURSOR_MOVED, xpos, ypos);
    }

    public static MousePositionInput getInstance() {
        if (instance == null) {
            instance = new MousePositionInput();
        }

        return instance;
    }
}
