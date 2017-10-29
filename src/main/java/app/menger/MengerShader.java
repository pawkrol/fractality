package app.menger;

import engine.scene.shader.Shader;
import modules.ProjectionShader;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class MengerShader extends ProjectionShader {

    private Vector3f lightPosition = new Vector3f(0f, 1.f, 2f);
    private Vector3f currentLightPosition = new Vector3f();
    private Vector4f currentLightPositionBuff = new Vector4f();

    public MengerShader(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
        addShader( new Shader("shaders/menger/menger_fragment_shader.frag", GL_FRAGMENT_SHADER) );
        addShader( new Shader("shaders/menger/menger_vertex_shader.vert", GL_VERTEX_SHADER) );
    }

    public void setColor(Vector3f color) {
        setUniform("color", color);
    }

    @Override
    public void createUniforms() throws Exception {
        super.createUniforms();
        createUniform("lightPos");
        createUniform("color");
    }

    @Override
    public void updateModelAndViewMatrix(Matrix4f modelMatrix, Matrix4f viewMatrix) {
        super.updateModelAndViewMatrix(modelMatrix, viewMatrix);

        currentLightPositionBuff.set(lightPosition, 1);
        currentLightPositionBuff.mul(viewMatrix);
        currentLightPosition.x = currentLightPositionBuff.x;
        currentLightPosition.y = currentLightPositionBuff.y;
        currentLightPosition.z = currentLightPositionBuff.z;
        setUniform("lightPos", currentLightPosition);
    }
}
