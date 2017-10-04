package app.menger;

import engine.model.*;
import engine.scene.GameObject;
import engine.scene.Scene;
import org.joml.Vector3f;

import java.util.ArrayList;

public class Box extends GameObject {

    public Box(Scene scene, MengerShader mengerShader) {
        super(scene, mengerShader);
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
        vertices.add(new Vertex(new Vector3f(-1f,  1f,  1f)));
        vertices.add(new Vertex(new Vector3f(-1f, -1f,  1f)));
        vertices.add(new Vertex(new Vector3f(1f, -1f,  1f)));
        vertices.add(new Vertex(new Vector3f(1f,  1f,  1f)));
        vertices.add(new Vertex(new Vector3f(-1f,  1f, -1f)));
        vertices.add(new Vertex(new Vector3f(1f,  1f, -1f)));
        vertices.add(new Vertex(new Vector3f(-1f, -1f, -1f)));
        vertices.add(new Vertex(new Vector3f(1f, -1f, -1f)));

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

}