package engine.message;

@FunctionalInterface
public interface MessageObserver {
    /**
     * This is where the messages are pushed.
     * Message id is defined in Message class.
     *
     * The argument params is optional, it may be used as in keyboard input
     * for passing a key that has changed its state.
     *
     * @param message A message being send
     * @param params Optional parameter of the message
     */
    void receiveMessage(int message, Object... params);
}
