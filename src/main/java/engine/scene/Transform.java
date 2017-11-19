package engine.scene;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform extends Node {

    private Matrix4f localMatrix;

    private Quaternionf rotation;
    private Vector3f translation;
    private Vector3f scale;

    private boolean dirty;

    public Transform() {
        this.type = Type.TRANSFORMATION;

        this.localMatrix = new Matrix4f();

        this.rotation = new Quaternionf();
        this.translation = new Vector3f();
        this.scale = new Vector3f(1, 1, 1);

        this.dirty = true;
    }

    public void getMatrix(Matrix4f transformationMatrix) {
        if (dirty) {
            localMatrix
                    .identity()
                    .translate(translation)
                    .rotate(rotation)
                    .scale(scale);

            dirty = false;
        }

        transformationMatrix.mul(localMatrix);
    }

    public Quaternionf getRotation() {
        dirty = true;
        return rotation;
    }

    public Vector3f getTranslation() {
        dirty = true;
        return translation;
    }

    public Vector3f getScale() {
        dirty = true;
        return scale;
    }
}
