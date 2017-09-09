package engine.scene;

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

    public void update() {

    }

    public void apply(Transform transform) {
        rotation.add(transform.rotation);
        translation.add(transform.translation);
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
