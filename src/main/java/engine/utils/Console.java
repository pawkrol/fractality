package engine.utils;

import engine.core.Window;
import engine.event.Event;
import engine.event.EventBus;
import engine.event.EventObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console implements EventObserver {

    private final EventBus eventBus;
    private final Scanner scanner;
    private Map<Integer, String> messagesNames;
    private Map<String, Integer> commands;

    public Console() {
        this.eventBus = EventBus.getInstance();
        this.scanner = new Scanner(System.in);
        this.initMessagesNames();
        this.initCommands();

        eventBus.attach(this);
    }

    @Override
    public void receiveEvent(int message, Object... params) {
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

                if (message == Event.KEY_PRESSED || message == Event.KEY_RELEASED) {
                    params = new Object[]{Integer.parseInt(values[1])};
                }

                eventBus.propagate(message, params);

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

        messagesNames.put(Event.KEY_PRESSED, "Key pressed, value =");
        messagesNames.put(Event.KEY_RELEASED, "Key released, value =");
        messagesNames.put(Event.CURSOR_MOVED, "Cursor moved to pos(x, y) =");
        messagesNames.put(Event.MOUSE_BUTTON_PRESSED, "Mouse button pressed, value =");
        messagesNames.put(Event.MOUSE_BUTTON_RELEASED, "Mouse button released, value =");
    }

    private void initCommands() {
        commands = new HashMap<>();

        commands.put("press", Event.KEY_PRESSED);
        commands.put("release", Event.KEY_RELEASED);
    }
}
