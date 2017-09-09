package engine.core;

import engine.scene.Scene;

class RenderManager {

    private Scene scene;

    void init() {
        if (scene == null) {
            throw new NullPointerException("Scene not set");
        }
    }

    void update() {
        scene.update();
    }

    void render() {
        scene.render();
    }

    Scene getScene() {
        return scene;
    }

    void setScene(Scene scene) {
        this.scene = scene;
    }
}
