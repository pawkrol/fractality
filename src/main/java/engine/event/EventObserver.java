package engine.event;

@FunctionalInterface
public interface EventObserver {
    /**
     * This is where the events are pushed.
     * Event id is defined in Event class.
     *
     * The argument 'params' is optional, it may be used as in keyboard input
     * for passing a key that has changed its state.
     *
     * @param event A event being send
     * @param params Optional parameter of the event
     */
    void receiveEvent(int event, Object... params);
}
