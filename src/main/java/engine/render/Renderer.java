package engine.render;

import engine.scene.GameObject;
import engine.scene.Transform;
import engine.scene.shader.ShaderProgram;

public class Renderer {

    private RenderConfig renderConfig;
    private ShaderProgram activeShaderProgram;

    public Renderer(RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
    }

    public void render(Transform transform, GameObject gameObject) {
        renderConfig.enable();

        bindShaderProgram(gameObject.getShaderProgram());

        renderConfig.disable();
    }

    private void bindShaderProgram(ShaderProgram shaderProgram) {
        if (activeShaderProgram == null
                || activeShaderProgram.getId() != shaderProgram.getId()) {
            activeShaderProgram = shaderProgram;
            activeShaderProgram.bind();
        }
    }
}
