package app;

import engine.model.Mesh;
import engine.model.Model;
import engine.model.Vertex;
import engine.scene.GameObject;
import engine.scene.Scene;
import engine.scene.shader.ShaderProgram;
import org.joml.Vector3f;

import java.util.ArrayList;

public class Triangle extends GameObject{

    public Triangle(Scene scene, ShaderProgram shaderProgram) {
        super(scene, null, shaderProgram);

        ArrayList<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(new Vector3f(0.25f, -0.25f, 0.5f)));
        vertices.add(new Vertex(new Vector3f(-0.25f, -0.25f, 0.5f)));
        vertices.add(new Vertex(new Vector3f(0.25f,  0.25f, 0.5f)));

        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(0);
        indices.add(1);
        indices.add(2);

        Mesh mesh = new Mesh(vertices, indices);
        Model model = new Model();
        model.setMesh(mesh);
        setModel(model);
    }
}
