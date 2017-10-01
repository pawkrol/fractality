package app.test;

import engine.scene.shader.Shader;
import modules.ProjectionShader;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class TestShaderProgram extends ProjectionShader {

    public TestShaderProgram(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
        addShader( new Shader("shaders/test/test_fragment_shader.frag", GL_FRAGMENT_SHADER) );
        addShader( new Shader("shaders/test/test_vertex_shader.vert", GL_VERTEX_SHADER) );
    }

}
