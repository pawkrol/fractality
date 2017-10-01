package app.test;

import engine.model.Mesh;
import engine.model.Model;
import engine.model.Vertex;
import engine.scene.GameObject;
import engine.scene.Scene;
import org.joml.Vector3f;

import java.util.ArrayList;

public class Triangle extends GameObject {

    public Triangle(Scene scene, TestShaderProgram testShaderProgram) {
        super(scene, testShaderProgram);
        init();
    }

    @Override
    public void update() {

    }

    private void init() {
        Model model = new Model();
        model.setMesh( createMesh() );
        setModel(model);
    }

    private Mesh createMesh() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(new Vector3f(0.25f, -0.25f, -1.05f)));
        vertices.add(new Vertex(new Vector3f(-0.25f, -0.25f, -1.05f)));
        vertices.add(new Vertex(new Vector3f(0.25f,  0.25f, -1.05f)));

        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(0);
        indices.add(1);
        indices.add(2);

        return new Mesh(vertices, indices);
    }

    @Override
    public TestShaderProgram getShaderProgram() {
        return (TestShaderProgram) super.getShaderProgram();
    }
}
