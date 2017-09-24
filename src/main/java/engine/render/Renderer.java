package engine.render;

import engine.scene.GameObject;
import engine.scene.Transform;
import engine.scene.shader.ShaderProgram;

public class Renderer {

    private Camera camera;
    private FrameConfig frameConfig;
    private RenderConfig renderConfig;
    private ShaderProgram activeShaderProgram;

    public Renderer(Camera camera, FrameConfig frameConfig, RenderConfig renderConfig) {
        this.camera = camera;
        this.frameConfig = frameConfig;
        this.renderConfig = renderConfig;
    }

    public void update() {
        frameConfig.initFrame();
        camera.update();
    }

    public void render(Transform transform, GameObject gameObject) {
        renderConfig.enable();

        ShaderProgram shaderProgram = gameObject.getShaderProgram();
        bindShaderProgram(shaderProgram);
        shaderProgram.updateModelAndViewMatrix(
                transform.getTransformationMatrix(),
                camera.getViewMatrix()
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
