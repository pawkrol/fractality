package app;

import engine.core.Game;

public class TestApp {

    public static void main(String[] args) {
        Game game = new Game();
        game.createWindow(1200, 800, "fractality", false);
        game.start();
    }

}
