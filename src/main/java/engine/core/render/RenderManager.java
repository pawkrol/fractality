package engine.core.render;

import engine.scene.GameObject;
import engine.scene.Node;
import engine.scene.Scene;
import engine.scene.Transform;
import engine.utils.MatrixStack;
import org.joml.Matrix4f;

import java.util.List;

public class RenderManager {

    private final int INIT_STACK_SIZE = 2;

    private Scene scene;
    private Renderer renderer;
    private MatrixStack matrixStack;
    private Matrix4f transformationMatrix;

    public void init() {
        if (scene == null) {
            throw new NullPointerException("Scene not set");
        }

        if (renderer == null) {
            throw new NullPointerException("Renderer not set");
        }

        createStack();
        transformationMatrix = new Matrix4f();

        renderer.init();
    }

    public void update() {
        renderer.update();
    }

    public void render() {
        resetMatrix();
        traverseSceneGraph(scene.getScenegraph().getRoot());
        renderer.postRender();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    private void traverseSceneGraph(Node node) { //pre order traversal
        if (node == null) return;

        pushMatrix();

        node.update();

        switch (node.getType()) {
            case OBJECT:
                renderer.render(transformationMatrix, (GameObject) node);
                break;
            case TRANSFORMATION:
                ((Transform) node).getMatrix(transformationMatrix);
                break;
        }

        List<Node> children = node.getChildren();
        synchronized (children) {
            for (Node child: children) {
                traverseSceneGraph(child);
            }
        }

        popMatrix();
    }

    private void createStack() {
        matrixStack = new MatrixStack(INIT_STACK_SIZE);
    }

    private void resetMatrix() {
        transformationMatrix.identity();
    }

    private void pushMatrix() {
        matrixStack.push(transformationMatrix);
    }

    private void popMatrix() {
        matrixStack.pop(transformationMatrix);
    }
}
