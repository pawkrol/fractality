package engine.model;

import engine.buffer.VAO;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    private VAO vao;

    public Mesh(ArrayList<Vertex> vertices, ArrayList<Integer> indices) {
        this.vao = new VAO(vertices, indices);
    }

    public void setInstances(int instances) {
        vao.setInstances(instances);
    }

    public void setInstanceDatai(List<Integer> data) {
        try {
            vao.setInstanceDatai(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInstanceDatav2f(List<Vector2f> data) {
        try {
            vao.setInstanceDatav2f(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInstanceDatav3f(List<Vector3f> data) {
        try {
            vao.setInstanceDatav3f(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        vao.draw();
    }
}
