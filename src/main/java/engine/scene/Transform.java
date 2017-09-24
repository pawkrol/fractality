package engine.scene;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform extends Node {

    private Quaternionf rotation;
    private Vector3f translation;
    private Vector3f scale;

    public Transform() {
        this.type = Type.TRANSFORMATION;

        this.rotation = new Quaternionf();
        this.translation = new Vector3f();
        this.scale = new Vector3f(1, 1, 1);
    }

    public Transform(Transform transform) {
        this.type = Type.TRANSFORMATION;

        this.rotation = new Quaternionf(transform.rotation);
        this.translation = new Vector3f(transform.translation);
        this.scale = new Vector3f(transform.scale);
    }

    public Matrix4f getTransformationMatrix() {
        return new Matrix4f()
                .translate(translation)
                .rotate(rotation)
                .scale(scale);
    }

    public void apply(Transform transform) {
        transform.rotation.mul(rotation, rotation);
        translation.add(transform.translation);
        scale.set(transform.scale);
    }

    public void set(Transform transform) {
        rotation.set(transform.rotation);
        translation.set(transform.translation);
        scale.set(transform.scale);
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
}
