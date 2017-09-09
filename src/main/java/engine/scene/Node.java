package engine.scene;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    public enum Type {
        TRANSFORMATION,
        OBJECT
    }

    protected Type type;

    private Node parent;
    private List<Node> children;

    protected Node() {
        children = new ArrayList<>();
    }

    public void update() {}

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node addChildren(Node node) {
        node.setParent(this);
        children.add(node);
        return node;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void clearChildren() {
        children.clear();
    }

    public Type getType() {
        return type;
    }
}
