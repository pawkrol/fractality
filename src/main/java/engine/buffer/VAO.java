package engine.buffer;

import engine.model.Mesh;
import engine.model.Vertex;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class VAO {

    private int id;
    private int vertexCount;

    public int getId() {
        return id;
    }

    public VAO(List<Vertex> vertices, List<Integer> indices) {
        vertexCount = indices.size();
        create(vertices, indices);
    }

    public void draw() {
        glBindVertexArray(id);

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT,0);

        glBindVertexArray(0);
    }

    private void create(List<Vertex> vertices, List<Integer> indices) {
        id = glCreateVertexArrays();

        glVertexArrayAttribBinding(id, 0,0);
        glVertexArrayAttribFormat(id, 0, Vertex.POSITION_SIZE, GL_FLOAT,
                false, Vertex.POSITION_RELATIVE_OFFSET);
        glEnableVertexArrayAttrib(id, 0);

        glVertexArrayAttribBinding(id, 1,0);
        glVertexArrayAttribFormat(id, 0, Vertex.TEXTURE_COORD_SIZE, GL_FLOAT,
                false, Vertex.TEXTURE_RELATIVE_COORD_OFFSET);
        glEnableVertexArrayAttrib(id, 1);

        glVertexArrayAttribBinding(id, 2,0);
        glVertexArrayAttribFormat(id, 0, Vertex.NORMAL_SIZE, GL_FLOAT,
                false, Vertex.NORMAL_RELATIVE_OFFSET);
        glEnableVertexArrayAttrib(id, 2);

        try ( MemoryStack stack = stackPush() ) {
            FloatBuffer floatBuffer = stack.mallocFloat(Vertex.SIZE * vertices.size());
            int verticesVBO = new VBO(floatBuffer).getId();
            glVertexArrayVertexBuffer(id, 0, verticesVBO, 0, Vertex.BYTES);

            IntBuffer intBuffer = stack.mallocInt(indices.size());
            int indicesVBO = new VBO(intBuffer).getId();
            glVertexArrayElementBuffer(id, indicesVBO);
        }
    }
}
