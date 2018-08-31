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

    public int getHeight() {
        return getHeight(root, 0);
    }

    public void clear() {
        clear(root);
        root = null;
    }

    private int getHeight(Node node, int height) {
        if (node == null) return height;

        int _height = height;

        if (node.getChildren().size() > 0) {
            _height += 1;
        }

        for (Node n: node.getChildren()) {
            int current_height = getHeight(n, _height);
            if (current_height > height) {
                height = current_height;
            }
        }

        return height;
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
