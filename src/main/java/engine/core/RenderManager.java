package engine.core;

import engine.render.Renderer;
import engine.scene.GameObject;
import engine.scene.Node;
import engine.scene.Scene;
import engine.scene.Transform;
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
        resetMatrix();
        renderer.update();
        updateScenegraph(scene.getScenegraph().getRoot());
    }

    void render() {
        renderScenegraph(scene.getScenegraph().getRoot());
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

    private void updateScenegraph(Node node) {
        if (node == null) return;

        node.update();
        for (Node child: node.getChildren()) {
            updateScenegraph(child);
        }
    }

    private void renderScenegraph(Node node) { //pre order traversal
        if (node == null) return;

        pushMatrix();

        if (node.getType() == Node.Type.TRANSFORMATION) {
            ( (Transform) node ).applyOn(transformationMatrix);
        } else if (node.getType() == Node.Type.OBJECT) {
            renderer.render(transformationMatrix, (GameObject) node);
        }

        for (Node child: node.getChildren()) {
            renderScenegraph(child);
        }

        popMatrix();
    }

    private void resetMatrix() {
        transformationMatrix.identity();
    }

    private void pushMatrix() {
        matrixStack.push(transformationMatrix);
    }

    private void popMatrix() {
        transformationMatrix
                .identity()
                .set( matrixStack.pop() );
    }
}
