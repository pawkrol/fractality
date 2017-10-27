package engine.buffer;

import engine.model.Vertex;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL31.glDrawElementsInstanced;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class VAO {

    private final int POSITION_INDEX = 0;
    private final int TEXTURE_INDEX = 1;
    private final int NORMAL_INDEX = 2;
    private final int INSTANCE_INDEX = 3;

    private int id;
    private int vertexCount;

    private boolean instanced;
    private int instances;

    public int getId() {
        return id;
    }

    public VAO(List<Vertex> vertices, List<Integer> indices) {
        vertexCount = indices.size();
        create(vertices, indices);
    }

    public void draw() {
        glBindVertexArray(id);

        if (instanced) {
            glDrawElementsInstanced(GL_TRIANGLES,  vertexCount, GL_UNSIGNED_INT, 0, instances);
        } else {
            glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
        }

        glBindVertexArray(0);
    }

    public void setInstanceData(List<Vector3f> data) throws Exception {
        if (id == 0) throw new Exception("Buffer not created");

        try (MemoryStack stack = stackPush()){
            FloatBuffer floatBuffer = stack.mallocFloat(3 * data.size());
            data.forEach(v -> {
                floatBuffer.put(v.x);
                floatBuffer.put(v.y);
                floatBuffer.put(v.z);
            });
            floatBuffer.flip();

            int dataVBO = new VBO(floatBuffer).getId();
            glVertexArrayVertexBuffer(id, 1, dataVBO, 0, 3 * Float.BYTES);
        }

    }

    private void create(List<Vertex> vertices, List<Integer> indices) {
        id = glCreateVertexArrays();

        glVertexArrayAttribBinding(id, POSITION_INDEX, 0);
        glVertexArrayAttribFormat(id, POSITION_INDEX, Vertex.POSITION_SIZE, GL_FLOAT,
                false, Vertex.POSITION_RELATIVE_OFFSET);
        glEnableVertexArrayAttrib(id, POSITION_INDEX);

        glVertexArrayAttribBinding(id, TEXTURE_INDEX, 0);
        glVertexArrayAttribFormat(id, TEXTURE_INDEX, Vertex.TEXTURE_COORD_SIZE, GL_FLOAT,
                false, Vertex.TEXTURE_COORD_RELATIVE_OFFSET);
        glEnableVertexArrayAttrib(id, TEXTURE_INDEX);

        glVertexArrayAttribBinding(id, NORMAL_INDEX, 0);
        glVertexArrayAttribFormat(id, NORMAL_INDEX, Vertex.NORMAL_SIZE, GL_FLOAT,
                false, Vertex.NORMAL_RELATIVE_OFFSET);
        glEnableVertexArrayAttrib(id, NORMAL_INDEX);

        try (MemoryStack stack = stackPush()) {
            FloatBuffer floatBuffer = stack.mallocFloat(Vertex.SIZE * vertices.size());
            vertices.forEach(v -> floatBuffer.put(v.getAsFloatArray()));
            floatBuffer.flip();

            int verticesVBO = new VBO(floatBuffer).getId();
            glVertexArrayVertexBuffer(id, 0, verticesVBO, 0, Vertex.BYTES);

            IntBuffer intBuffer = stack.mallocInt(indices.size());
            indices.forEach(intBuffer::put);
            intBuffer.flip();

            int indicesVBO = new VBO(intBuffer).getId();
            glVertexArrayElementBuffer(id, indicesVBO);
        }
    }

    public boolean isInstanced() {
        return instanced;
    }

    public void setInstanced(boolean instanced) {
        this.instanced = instanced;

        if (instanced) {
            glVertexArrayAttribBinding(id, INSTANCE_INDEX, 1);
            glVertexArrayAttribFormat(id, INSTANCE_INDEX, 3, GL_FLOAT, false, 0);
            glVertexArrayBindingDivisor(id, 1, 1);
            glEnableVertexArrayAttrib(id, INSTANCE_INDEX);
        }
    }

    public int getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }
}