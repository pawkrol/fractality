package modules;

import engine.scene.shader.ShaderProgram;
import org.joml.Matrix4f;

public abstract class ProjectionShader extends ShaderProgram {

    private int windowWidth;
    private int windowHeight;

    private Matrix4f projectionMatrix;
    private Matrix4f pvmMatrix;

    private ProjectionShader() {}

    public ProjectionShader(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        pvmMatrix = new Matrix4f();
    }

    @Override
    public void create() {
        try {
            createAndLink();
            createUniforms();
            createProjectionMatrix();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModelAndViewMatrix(Matrix4f modelMatrix, Matrix4f viewMatrix) {
        setUniform(
                "pvmMatrix",
                pvmMatrix.set(projectionMatrix).mul(viewMatrix).mul(modelMatrix)
        );
    }

    public void createUniforms() throws Exception {
        createUniform("pvmMatrix");
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) windowWidth / (float) windowHeight;
        float fov = (float) Math.toRadians(50.f);
        float nearPlane = .01f;
        float farPlane = 100.f;
        projectionMatrix = new Matrix4f()
                .perspective(fov, aspectRatio, nearPlane, farPlane);
    }

}
