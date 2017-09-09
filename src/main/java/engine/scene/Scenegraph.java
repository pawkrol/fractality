package engine.scene;

public class Scenegraph {

    private Node root;

    public void update() {
        root.update();
    }

    public void render() {
        root.render();
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
