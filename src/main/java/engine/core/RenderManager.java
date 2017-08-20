package engine.core;

import engine.scene.Scene;

class RenderManager {

    private Scene scene;

    public void init() {
        if (scene == null) {
            throw new NullPointerException("Scene not set");
        }
    }

    public void update() {

    }

    public void render() {

    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
