package engine.scene;

public class GameObject extends Node {

    private Scene scene;

    public GameObject(Scene scene) {
        this.type = Type.OBJECT;

        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

}
