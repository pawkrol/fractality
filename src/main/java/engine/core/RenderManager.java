package engine.core;

import engine.render.Renderer;
import engine.scene.GameObject;
import engine.scene.Node;
import engine.scene.Scene;
import engine.scene.Transform;

class RenderManager {

    private Scene scene;
    private Renderer renderer;

    void init() {
        if (scene == null) {
            throw new NullPointerException("Scene not set");
        }

        if (renderer == null) {
            throw new NullPointerException("Renderer not set");
        }
    }

    void update() {

    }

    void render() {
        renderScenegraph(
                null,
                scene.getScenegraph().getRoot()
        );
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

    private void renderScenegraph(Transform currentTransform, Node node) { //pre order traversal
        if (node == null) return;

        node.update();

        if (node.getType() == Node.Type.TRANSFORMATION) {
            if (currentTransform == null) {
                currentTransform = (Transform) node;
            } else {
               currentTransform.apply((Transform) node);
            }
        } else if (node.getType() == Node.Type.OBJECT) {
            renderer.render( currentTransform, (GameObject) node);
        }

        for (Node child: node.getChildren()) {
            renderScenegraph(currentTransform, child);
        }
    }
}
