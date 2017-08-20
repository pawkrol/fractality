package engine.scene;

public class Scene {

    private SceneGraph sceneGraph;

    public Scene() {
        this.sceneGraph = new SceneGraph();
    }

    public SceneGraph getSceneGraph() {
        return sceneGraph;
    }
}
