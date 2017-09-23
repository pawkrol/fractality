package engine.buffer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL30.GL_MAP_READ_BIT;
import static org.lwjgl.opengl.GL45.*;

public class VBO {

    private int id;

    public int getId() {
        return id;
    }

    public VBO(FloatBuffer floatBuffer) {
        create(floatBuffer);
    }

    public VBO(IntBuffer intBuffer) {
        create(intBuffer);
    }

    private void create(FloatBuffer floatBuffer) {
        id = glCreateBuffers();
        glNamedBufferStorage(id, floatBuffer, GL_MAP_READ_BIT);
    }

    private void create(IntBuffer intBuffer) {
        id = glCreateBuffers();
        glNamedBufferStorage(id, intBuffer, GL_MAP_READ_BIT);
    }
}
