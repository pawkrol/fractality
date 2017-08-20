package engine.message;

import java.util.LinkedList;
import java.util.List;

public class MessageBus {

    private static MessageBus instance;

    private List<MessageObserver> observers;

    private MessageBus() {
        observers = new LinkedList<>();
    }

    public void attach(MessageObserver messageObserver) {
        observers.add(messageObserver);
    }

    public void detach(MessageObserver messageObserver) {
        observers.remove(messageObserver);
    }

    public void propagate(int message, Object... params) {
        observers.forEach(o -> o.receiveMessage(message, params));
    }

    public static MessageBus getInstance() {
        if (instance == null) {
            instance = new MessageBus();
        }

        return instance;
    }
}
