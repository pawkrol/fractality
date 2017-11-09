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

    public void clear() {
        clear(root);
        root = null;
    }

    private void clear(Node node) {
        if (node == null) return;

        if (node.getChildren().isEmpty()) {
            node.setParent(null);
            return;
        }

        for (Node child: node.getChildren()) {
            clear(child);
            child.clearChildren();
            child.setParent(null);
        }
    }
}
