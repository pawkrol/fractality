package app;

import engine.scene.shader.Shader;
import engine.scene.shader.ShaderProgram;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class TestShaderProgram extends ShaderProgram {

    private int windowWidth;
    private int windowHeight;

    public TestShaderProgram(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        addShader( new Shader("test_fragment_shader.frag", GL_FRAGMENT_SHADER) );
        addShader( new Shader("test_vertex_shader.vert", GL_VERTEX_SHADER) );
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
        setUniform("viewMatrix", viewMatrix);
        setUniform("modelMatrix", modelMatrix);
    }

    public void setColor(Vector3f color) {
        setUniform("color", color);
    }

    public void createUniforms() throws Exception {
        createUniform("projectionMatrix");
        createUniform("viewMatrix");
        createUniform("modelMatrix");
        createUniform("color");
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) windowWidth / (float) windowHeight;
        float fov = (float) Math.toRadians(50.f);
        float nearPlane = .01f;
        float farPlane = 100.f;
        Matrix4f projectionMatrix = new Matrix4f()
                .perspective(fov, aspectRatio, nearPlane, farPlane);
        bind();
        setUniform("projectionMatrix", projectionMatrix);
        unbind();
    }
}
