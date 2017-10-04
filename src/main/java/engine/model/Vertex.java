package engine.model;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex {

    public static final int POSITION_SIZE = 3;
    public static final int TEXTURE_COORD_SIZE = 2;
    public static final int NORMAL_SIZE = 3;

    public static final int POSITION_RELATIVE_OFFSET = 0;
    public static final int TEXTURE_COORD_RELATIVE_OFFSET = POSITION_SIZE * Float.BYTES;
    public static final int NORMAL_RELATIVE_OFFSET = TEXTURE_COORD_RELATIVE_OFFSET + TEXTURE_COORD_SIZE * Float.BYTES;

    public static final int SIZE = POSITION_SIZE + TEXTURE_COORD_SIZE + NORMAL_SIZE;
    public static final int BYTES = SIZE * Float.BYTES;

    private Vector3f position;
    private Vector2f textureCoord;
    private Vector3f normal;

    public Vertex(Vector3f position) {
        this(position, new Vector2f(0, 0), new Vector3f(0, 0, 0));
    }

    public Vertex(Vector3f position, Vector3f normal) {
        this(position, new Vector2f(0, 0), normal);
    }

    public Vertex(Vector3f position, Vector2f textureCoord) {
        this(position, textureCoord, new Vector3f(0, 0, 0));
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

    public float[] getAsFloatArray() {
        return new float[]{
                position.x, position.y, position.z,
                textureCoord.x, textureCoord.y,
                normal.x, normal.y, normal.z
        };
    }
}
