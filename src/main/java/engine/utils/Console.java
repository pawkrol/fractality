package engine.utils;

import engine.core.Window;
import engine.message.Message;
import engine.message.MessageBus;
import engine.message.MessageObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console implements MessageObserver{

    private final MessageBus messageBus;
    private final Scanner scanner;
    private Map<Integer, String> messagesNames;
    private Map<String, Integer> commands;

    public Console() {
        this.messageBus = MessageBus.getInstance();
        this.scanner = new Scanner(System.in);
        this.initMessagesNames();
        this.initCommands();

        messageBus.attach(this);
    }

    @Override
    public void receiveMessage(int message, Object... params) {
        StringBuilder log = new StringBuilder();

        log.append(messagesNames.get(message));
        for (Object o: params) {
            log.append("\t").append(o);
        }
        log.append("\n");

        System.out.print(log);
    }

    public void initInput(Window window) {
        new Thread(() -> {
            while (!window.shouldClose()) {
                String input = scanner.nextLine();
                String[] values = input.split(" ");

                int message = commands.get(values[0]);
                Object[] params = null;

                if (message == Message.KEY_PRESSED || message == Message.KEY_RELEASED) {
                    params = new Object[]{Integer.parseInt(values[1])};
                }

                messageBus.propagate(message, params);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initMessagesNames() {
        messagesNames = new HashMap<>();

        messagesNames.put(Message.KEY_PRESSED, "Key pressed, value =");
        messagesNames.put(Message.KEY_RELEASED, "Key released, value =");
        messagesNames.put(Message.CURSOR_MOVED, "Cursor moved to pos(x, y) =");
        messagesNames.put(Message.MOUSE_BUTTON_PRESSED, "Mouse button pressed, value =");
        messagesNames.put(Message.MOUSE_BUTTON_RELEASED, "Mouse button released, value =");
    }

    private void initCommands() {
        commands = new HashMap<>();

        commands.put("press", Message.KEY_PRESSED);
        commands.put("release", Message.KEY_RELEASED);
    }
}
