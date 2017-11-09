package engine.core;

import engine.render.Renderer;
import engine.scene.*;
import org.joml.Matrix4f;

import java.util.Stack;

class RenderManager {

    private Scene scene;
    private Renderer renderer;
    private Stack<Matrix4f> matrixStack;
    private Matrix4f transformationMatrix;

    void init() {
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

    void update() {
        renderer.update();
    }

    void render() {
        resetMatrix();
        traverseSceneGraph(scene.getScenegraph().getRoot());
    }

    Scene getScene() {
        return scene;
    }

    void setScene(Scene scene) {
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
            ( (Transform) node ).applyOn(transformationMatrix);
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
