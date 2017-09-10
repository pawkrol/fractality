package engine.scene;

import engine.scene.shader.ShaderProgram;

public class GameObject extends Node {

    private Scene scene;
    private ShaderProgram shaderProgram;

    public GameObject(Scene scene, ShaderProgram shaderProgram) {
        this.type = Type.OBJECT;

        this.scene = scene;
        this.shaderProgram = shaderProgram;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public void setShaderProgram(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }
}
