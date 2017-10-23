package app.menger;

import engine.scene.shader.Shader;
import modules.ProjectionShader;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class MengerShader extends ProjectionShader {

    private Vector3f lightPosition = new Vector3f(0f, 2.f, -2.f);

    public MengerShader(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
        addShader( new Shader("shaders/menger/menger_fragment_shader.frag", GL_FRAGMENT_SHADER) );
        addShader( new Shader("shaders/menger/menger_vertex_shader.vert", GL_VERTEX_SHADER) );
    }

    @Override
    public void createUniforms() throws Exception {
        super.createUniforms();
        createUniform("lightPos");
    }

    @Override
    public void updateModelAndViewMatrix(Matrix4f modelMatrix, Matrix4f viewMatrix) {
        super.updateModelAndViewMatrix(modelMatrix, viewMatrix);
        Vector4f aux = new Vector4f(lightPosition, 1);
        aux.mul(viewMatrix);
        Vector3f currentLight = new Vector3f();
        currentLight.x = aux.x;
        currentLight.y = aux.y;
        currentLight.z = aux.z;
        setUniform("lightPos", currentLight);
    }
}
