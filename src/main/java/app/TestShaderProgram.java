package app;

import engine.scene.shader.Shader;
import engine.scene.shader.ShaderProgram;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class TestShaderProgram extends ShaderProgram {

    public TestShaderProgram() {
        addShader( new Shader("test_fragment_shader.frag", GL_FRAGMENT_SHADER) );
        addShader( new Shader("test_vertex_shader.vert", GL_VERTEX_SHADER) );
    }

    public void setColor(Vector3f color) {
        setUniform("color", color);
    }

    public void createUniforms() throws Exception {
        createUniform("color");
    }
}
