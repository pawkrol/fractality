package engine.scene;

import org.joml.Matrix4f;

public abstract class EngineCamera {
    protected Matrix4f viewMatrix;

    public abstract void update();
    public abstract Matrix4f getViewMatrix();
}
