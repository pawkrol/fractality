package engine.event;

import java.util.LinkedList;
import java.util.List;

public class EventBus {

    private static EventBus instance;

    private List<EventObserver> observers;

    private EventBus() {
        observers = new LinkedList<>();
    }

    public void attach(EventObserver eventObserver) {
        observers.add(eventObserver);
    }

    public void detach(EventObserver eventObserver) {
        observers.remove(eventObserver);
    }

    public void propagate(int event, Object... params) {
        observers.forEach(o -> o.receiveEvent(event, params));
    }

    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }

        return instance;
    }
}
