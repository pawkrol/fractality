package engine.scene;

public class Scene {

    private Scenegraph scenegraph;

    public Scene() {
        this.scenegraph = new Scenegraph();
    }

    public void update() {
        scenegraph.update();
    }

    public void render() {
        scenegraph.render();
    }

    public Scenegraph getScenegraph() {
        return scenegraph;
    }
}
