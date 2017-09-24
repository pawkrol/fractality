package app;

import engine.model.Mesh;
import engine.model.Model;
import engine.model.Vertex;
import engine.scene.GameObject;
import engine.scene.Scene;
import org.joml.Vector3f;

import java.util.ArrayList;

public class Cube extends GameObject {

    public Cube(Scene scene, TestShaderProgram testShaderProgram) {
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

        getShaderProgram().bind();
        getShaderProgram().setColor(new Vector3f(1, 1, 0));
        getShaderProgram().unbind();
    }

    private Mesh createMesh() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f)));
        vertices.add(new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f)));
        vertices.add(new Vertex(new Vector3f(0.5f, -0.5f,  0.5f)));
        vertices.add(new Vertex(new Vector3f(0.5f,  0.5f,  0.5f)));
        vertices.add(new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f)));
        vertices.add(new Vertex(new Vector3f(0.5f,  0.5f, -0.5f)));
        vertices.add(new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f)));
        vertices.add(new Vertex(new Vector3f(0.5f, -0.5f, -0.5f)));

        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(0);
        indices.add(1);
        indices.add(3);
        indices.add(3);
        indices.add(1);
        indices.add(2);
        indices.add(4);
        indices.add(0);
        indices.add(3);
        indices.add(5);
        indices.add(4);
        indices.add(3);
        indices.add(3);
        indices.add(2);
        indices.add(7);
        indices.add(5);
        indices.add(3);
        indices.add(7);
        indices.add(6);
        indices.add(1);
        indices.add(0);
        indices.add(6);
        indices.add(0);
        indices.add(4);
        indices.add(2);
        indices.add(1);
        indices.add(6);
        indices.add(2);
        indices.add(6);
        indices.add(7);
        indices.add(7);
        indices.add(6);
        indices.add(4);
        indices.add(7);
        indices.add(4);
        indices.add(5);

        return new Mesh(vertices, indices);
    }

    @Override
    public TestShaderProgram getShaderProgram() {
        return (TestShaderProgram) super.getShaderProgram();
    }

}
