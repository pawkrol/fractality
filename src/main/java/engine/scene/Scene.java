package engine.scene;

public class Scene {

    private Scenegraph scenegraph;
    private Camera camera;

    public Scene() {
        this.scenegraph = new Scenegraph();
    }

    public Scenegraph getScenegraph() {
        return scenegraph;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
