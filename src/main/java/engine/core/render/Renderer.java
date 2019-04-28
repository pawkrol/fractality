package engine.core.render;

import engine.model.Model;
import engine.scene.EngineCamera;
import engine.scene.GameObject;
import engine.scene.shader.ShaderProgram;
import org.joml.Matrix4f;

public class Renderer {

    private EngineCamera camera;
    private ShaderProgram activeShaderProgram;

    private FrameConfig frameConfig;
    private RenderConfig renderConfig;
    private DrawCallConfig drawCallConfig;

    private Matrix4f viewMatrix;

    private Renderer(EngineCamera camera, RenderConfig renderConfig,
                     FrameConfig frameConfig, DrawCallConfig drawCallConfig) {
        this.camera = camera;
        this.frameConfig = frameConfig;
        this.renderConfig = renderConfig;
        this.drawCallConfig = drawCallConfig;
    }

    public void init() {
        renderConfig.enable();
    }

    public void update() {
        frameConfig.initFrame();
        camera.update();
        viewMatrix = camera.getViewMatrix();
    }

    public void render(Matrix4f transformationMatrix, GameObject gameObject) {
        drawCallConfig.enable();

        ShaderProgram shaderProgram = gameObject.getShaderProgram();
        Model model = gameObject.getModel();

        bindShaderProgram(shaderProgram);
        shaderProgram.updateModelViewMatrix(
                transformationMatrix,
                viewMatrix
        );
        gameObject.updateUniforms();

        model.bind();
        model.draw();

        drawCallConfig.disable();
    }

    private void bindShaderProgram(ShaderProgram shaderProgram) {
        if (activeShaderProgram == null
                || activeShaderProgram.getId() != shaderProgram.getId()) {
            activeShaderProgram = shaderProgram;
            activeShaderProgram.bind();
        }
    }

    public static class RendererBuilder {

        private FrameConfig frameConfig;
        private RenderConfig renderConfig;
        private DrawCallConfig drawCallConfig;
        private EngineCamera camera;

        public RendererBuilder frameConfig(FrameConfig frameConfig) {
            this.frameConfig = frameConfig;
            return this;
        }

        public RendererBuilder renderConfig(RenderConfig renderConfig) {
            this.renderConfig = renderConfig;
            return this;
        }

        public RendererBuilder drawCallConfig(DrawCallConfig drawCallConfig) {
            this.drawCallConfig = drawCallConfig;
            return this;
        }

        public RendererBuilder camera(EngineCamera camera) {
            this.camera = camera;
            return this;
        }

        public Renderer build() {
            if (camera == null) {
                throw new NullPointerException("Camera not set");
            }
            if (renderConfig == null) {
                throw new NullPointerException("Render config not set");
            }
            if (drawCallConfig == null) {
                throw new NullPointerException("Draw call config not set");
            }
            if (frameConfig == null) {
                throw new NullPointerException("Frame config not set");
            }

            return new Renderer(camera, renderConfig, frameConfig, drawCallConfig);
        }
    }
}
