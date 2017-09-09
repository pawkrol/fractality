package engine.scene;

public class GameObject extends Node {

    private Scene scene;

    public GameObject(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

}
