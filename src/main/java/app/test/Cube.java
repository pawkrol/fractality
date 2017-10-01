package app.test;

import engine.model.*;
import engine.scene.GameObject;
import engine.scene.Scene;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;

import static engine.common.Image.Type.TYPE_ALPHA;

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
        model.setMaterial( createMaterial() );
        setModel(model);
    }

    private Mesh createMesh() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        vertices.add(
                new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(0, 0))
        );
        vertices.add(
                new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0, 1))
        );
        vertices.add(
                new Vertex(new Vector3f(0.5f, -0.5f,  0.5f), new Vector2f(1, 1))
        );
        vertices.add(
                new Vertex(new Vector3f(0.5f,  0.5f,  0.5f), new Vector2f(1, 0))
        );
        vertices.add(
                new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0, 0))
        );
        vertices.add(
                new Vertex(new Vector3f(0.5f,  0.5f, -0.5f), new Vector2f(0, 1))
        );
        vertices.add(
                new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(1, 1))
        );
        vertices.add(
                new Vertex(new Vector3f(0.5f, -0.5f, -0.5f), new Vector2f(1, 0))
        );

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

    private Material createMaterial() {
        Material material = new Material();
        material.setTexture(TextureLoader.load("textures/wood.png", TYPE_ALPHA));

        return material;
    }

    @Override
    public TestShaderProgram getShaderProgram() {
        return (TestShaderProgram) super.getShaderProgram();
    }

}
