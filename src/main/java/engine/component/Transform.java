package engine.component;

import org.joml.AxisAngle4f;
import org.joml.Vector3f;

public class Transform extends Component{

    private AxisAngle4f rotation;
    private Vector3f translation;
    private Vector3f scale;

    public Transform() {
        this.setType(Type.TRANSFORM);

        this.rotation = new AxisAngle4f(0, 0, 0, 0);
        this.translation = new Vector3f(0, 0, 0);
        this.scale = new Vector3f(1, 1, 1);
    }

    public AxisAngle4f getRotation() {
        return rotation;
    }

    public void setRotation(AxisAngle4f rotation) {
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
