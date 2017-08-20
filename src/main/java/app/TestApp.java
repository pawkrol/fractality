package app;

import engine.core.Game;
import engine.scene.Scene;

public class TestApp {

    public static void main(String[] args) {
        Game game = new Game();
        game.createWindow(1200, 800, "fractality", false);

        Scene scene = new Scene();
        game.setScene(scene);

        game.start();
    }

}
