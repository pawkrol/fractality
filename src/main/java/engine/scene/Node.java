package engine.scene;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    private Node parent;
    private List<Node> children;

    public Node() {
        children = new ArrayList<>();
    }

    public void update() {
        for (Node n: children) {
            n.update();
        }
    }

    public void render() {
        for (Node n: children) {
            n.render();
        }
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        parent.addChildren(this);
        this.parent = parent;
    }

    public void addChildren(Node node) {
        node.setParent(this);
        children.add(node);
    }

    public List<Node> getChildren() {
        return children;
    }

    public void clearChildren() {
        children.clear();
    }

}
