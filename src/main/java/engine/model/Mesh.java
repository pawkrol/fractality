package engine.model;

import engine.buffer.VAO;

import java.util.ArrayList;

public class Mesh {

    private VAO vao;

    public Mesh(ArrayList<Vertex> vertices, ArrayList<Integer> indices) {
        this.vao = new VAO(vertices, indices);
    }

    public void draw() {
        vao.draw();
    }
}
