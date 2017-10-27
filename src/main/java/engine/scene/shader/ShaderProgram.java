package engine.scene.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public abstract class ShaderProgram {

    private int id;
    private HashMap<String, Integer> uniforms;
    private List<Shader> shaders;

    public ShaderProgram() {
        uniforms = new HashMap<>();
        shaders = new LinkedList<>();
    }

    public abstract void updateModelAndViewMatrix(Matrix4f modelMatrix, Matrix4f viewMatrix);

    public abstract void create();

    public void bind() {
        glUseProgram(id);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public int getId() {
        return id;
    }

    protected void addShader(Shader shader) {
        shaders.add(shader);
    }

    protected void createAndLink() throws Exception {
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

    protected void createUniform(String uniformName) throws Exception {
        int uniformLocation = glGetUniformLocation(id, uniformName);
        if (uniformLocation < 0) {
//            throw new Exception("Could not create uniform: " + uniformName + ", code = " + uniformLocation);
        }

        uniforms.put(uniformName, uniformLocation);
    }

    protected void setUniform(String uniformName, Vector3f vector){
        int location = uniforms.get(uniformName);
        glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void setUniform(String uniformName, Vector4f vector){
        int location = uniforms.get(uniformName);
        glUniform4f(location, vector.x, vector.y, vector.z, vector.w);
    }

    protected void setUniform(String uniformName, float value){
        int location = uniforms.get(uniformName);
        glUniform1f(location, value);
    }

    protected void setUniform(String uniformName, int value){
        int location = uniforms.get(uniformName);
        glUniform1i(location, value);
    }

    protected void setUniform(String uniformName, Matrix4f matrix){
        try (MemoryStack stack = stackPush()) {
            FloatBuffer matrixBuffer = stack.mallocFloat(16);
            matrix.get(matrixBuffer);

            int location = uniforms.get(uniformName);
            glUniformMatrix4fv(location, false, matrixBuffer);
        }
    }
}
