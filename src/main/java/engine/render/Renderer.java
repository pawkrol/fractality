package engine.render;

import engine.scene.GameObject;
import engine.scene.Transform;
import engine.scene.shader.ShaderProgram;

public class Renderer {

    private FrameConfig frameConfig;
    private RenderConfig renderConfig;
    private ShaderProgram activeShaderProgram;

    public Renderer(FrameConfig frameConfig, RenderConfig renderConfig) {
        this.frameConfig = frameConfig;
        this.renderConfig = renderConfig;
    }

    public void initFrame() {
        frameConfig.initFrame();
    }

    public void render(Transform transform, GameObject gameObject) {
        renderConfig.enable();

        bindShaderProgram(gameObject.getShaderProgram());
        gameObject.getShaderProgram()
                .updateModelMatrix(
                        transform.getTransformationMatrix()
                );
        gameObject.getModel()
                .getMesh()
                .draw();

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
