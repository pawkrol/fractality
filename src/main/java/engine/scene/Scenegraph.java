package engine.scene;

public class Scenegraph {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public Node setRoot(Node root) {
        this.root = root;
        return root;
    }
}
