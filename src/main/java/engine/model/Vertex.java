package engine.model;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex {

    private Vector3f position;
    private Vector2f textureCoord;
    private Vector3f normal;

    public Vertex(Vector3f position) {
        this(position, new Vector2f(0, 0), new Vector3f(0, 0, 0));
    }

    public Vertex(Vector3f position, Vector2f textureCoord, Vector3f normal) {
        this.position = position;
        this.textureCoord = textureCoord;
        this.normal = normal;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector2f getTextureCoord() {
        return textureCoord;
    }

    public void setTextureCoord(Vector2f textureCoord) {
        this.textureCoord = textureCoord;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }
}
