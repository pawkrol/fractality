package engine.scene;

import engine.model.Model;
import engine.scene.shader.ShaderProgram;

public abstract class GameObject extends Node {

    private Scene scene;
    private ShaderProgram shaderProgram;
    private Model model;

    public GameObject(Scene scene) {
        this(scene, null, null);
    }

    public GameObject(Scene scene, ShaderProgram shaderProgram) {
        this(scene, null, shaderProgram);
    }

    public GameObject(Scene scene, Model model, ShaderProgram shaderProgram) {
        this.type = Type.OBJECT;

        this.scene = scene;
        this.model = model;
        this.shaderProgram = shaderProgram;
    }

    public void updateUniforms() {}

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    protected void setShaderProgram(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }
}
