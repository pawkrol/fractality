package app;

import engine.scene.shader.Shader;

import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class TestVertexShader extends Shader{

    public TestVertexShader() {
        super("test_vertex_shader.vert", GL_VERTEX_SHADER);
    }

}
