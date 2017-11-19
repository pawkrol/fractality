package engine.core.render;

import engine.scene.*;
import org.joml.Matrix4f;

import java.util.Stack;

public class RenderManager {

    private Scene scene;
    private Renderer renderer;
    private Stack<Matrix4f> matrixStack;
    private Matrix4f transformationMatrix;

    public void init() {
        if (scene == null) {
            throw new NullPointerException("Scene not set");
        }

        if (renderer == null) {
            throw new NullPointerException("Renderer not set");
        }

        matrixStack = new Stack<>();
        transformationMatrix = new Matrix4f();

        renderer.init();
    }

    public void update() {
        renderer.update();
    }

    public void render() {
        resetMatrix();
        traverseSceneGraph(scene.getScenegraph().getRoot());
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
        if (node.getType() == Node.Type.TRANSFORMATION) {
            ( (Transform) node ).getMatrix(transformationMatrix);
        } else if (node.getType() == Node.Type.OBJECT) {
            renderer.render(transformationMatrix, (GameObject) node);
        }

        for (Node child: node.getChildren()) {
            traverseSceneGraph(child);
        }

        popMatrix();
    }

    private void resetMatrix() {
        transformationMatrix.identity();
    }

    private void pushMatrix() {
        matrixStack.push(new Matrix4f(transformationMatrix));
    }

    private void popMatrix() {
        transformationMatrix = matrixStack.pop();
    }
}
