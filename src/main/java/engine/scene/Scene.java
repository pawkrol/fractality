package engine.scene;

public class Scene {

    private Scenegraph scenegraph;

    public Scene() {
        this.scenegraph = new Scenegraph();
    }

    public Scenegraph getScenegraph() {
        return scenegraph;
    }
}
