package engine.model;

import java.util.ArrayList;

public class Mesh {

    private ArrayList<Vertex> vertices;
    private ArrayList<Integer> indices;

    public Mesh(ArrayList<Vertex> vertices, ArrayList<Integer> indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Integer> getIndices() {
        return indices;
    }

    public void setIndices(ArrayList<Integer> indices) {
        this.indices = indices;
    }
}
