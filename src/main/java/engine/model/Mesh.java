package engine.model;

import engine.buffer.VAO;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    private VAO vao;

    public Mesh(ArrayList<Vertex> vertices, ArrayList<Integer> indices) {
        this.vao = new VAO(vertices, indices);
    }

    public void setInstanced(boolean instanced) {
        vao.setInstanced(instanced);
    }

    public void setInstances(int instances) {
        vao.setInstances(instances);
    }

    public void setInstanceData(List<Vector3f> data) {
        try {
            vao.setInstanceData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        vao.draw();
    }
}
