package engine.render;

import engine.scene.GameObject;
import engine.scene.Transform;

public class Renderer {

    private RenderConfig renderConfig;

    public Renderer(RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
    }

    public void render(Transform transform, GameObject gameObject) {
        renderConfig.enable();

        renderConfig.disable();
    }

}
