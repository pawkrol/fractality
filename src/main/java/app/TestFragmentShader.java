package app;

import engine.scene.shader.Shader;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;

public class TestFragmentShader extends Shader {

    public TestFragmentShader() {
        super("test_fragment_shader.frag", GL_FRAGMENT_SHADER);
    }

}
