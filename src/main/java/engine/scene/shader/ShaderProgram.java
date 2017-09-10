package engine.scene.shader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private int id;
    private HashMap<String, Integer> uniforms;
    private List<Shader> shaders;

    public ShaderProgram() throws Exception {
        uniforms = new HashMap<>();
        shaders = new LinkedList<>();
    }

    public void addShader(Shader shader) {
        shaders.add(shader);
    }

    public void bind() {
        glUseProgram(id);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void createAndLink() throws Exception {
        id = glCreateProgram();
        if (id == GL_FALSE) {
            throw new Exception("Failed to create shader program");
        }

        for (Shader shader: shaders) {
            int shaderId = shader.createAndCompile();
            glAttachShader(id, shaderId);
        }

        glLinkProgram(id);
        if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE) {
            throw new Exception("Failed to link shader program. Info: " + glGetProgramInfoLog(id));
        }

        for (Shader shader: shaders) {
            shader.clear();
        }
    }

    public int getId() {
        return id;
    }
}
