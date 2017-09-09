package engine.component;

import engine.scene.GameObject;

public abstract class Component {

    public enum Type {
        TRANSFORM,
        RENDER,
        SHADER,
        CAMERA
    }

    private Type type;
    private GameObject owner;

    public void execute() {}

    public GameObject getOwner() {
        return owner;
    }

    public void setOwner(GameObject owner) {
        this.owner = owner;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
