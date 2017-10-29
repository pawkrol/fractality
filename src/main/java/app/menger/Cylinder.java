package app.menger;

import engine.model.Mesh;
import engine.model.Model;
import engine.model.Vertex;
import engine.scene.GameObject;
import engine.scene.Scene;
import org.joml.Vector3f;

import java.util.ArrayList;

public class Cylinder extends GameObject{

    private float topR;
    private float bottomR;
    private float height;

    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Integer> indices = new ArrayList<>();

    private MengerShader mengerShader;
    private Vector3f color;

    public Cylinder(Scene scene, MengerShader mengerShader) {
        super(scene, mengerShader);

        this.mengerShader = mengerShader;
        this.color = new Vector3f(.26f, .15f, .07f);
    }

    public void generate(float topR, float bottomR, float height) {
        this.topR = topR;
        this.bottomR = bottomR;
        this.height = height;

        vertices.clear();
        indices.clear();

        Model model = new Model();
        model.setMesh( generateCylinder() );
        setModel(model);
    }

    public void updateUniforms() {
        mengerShader.setColor(color);
    }

    private Mesh generateCylinder() {
        float step = 2.5f;
        generateLid(vertices, step, bottomR, 0);
        generateLid(vertices, step, topR, height);

        int verticesPerLid = (int) Math.floor(360. / step);
        for (int j = 1; j < verticesPerLid + 1; j++) {
            indices.add(j);
            indices.add(j + 1);
            indices.add(verticesPerLid + 2 + j);

            indices.add(j + 1);
            indices.add(verticesPerLid + 2 + j);
            indices.add(verticesPerLid + 2 + j + 1);
        }

        return new Mesh(vertices, indices);
    }

    private void generateLid(ArrayList<Vertex> vertices, float step, float r, float y) {
        vertices.add(new Vertex( new Vector3f(0, y, 0)));
        for (float theta = 0; theta <= 360; theta += step) {
            float x = r * (float)Math.cos( Math.toRadians(theta) );
            float z = r * (float)Math.sin( Math.toRadians(theta) );
            vertices.add(new Vertex( new Vector3f(x, y, z), new Vector3f(x, 0, z).normalize() ));

//            if (i > 1) {
//                indices.add(0);
//                indices.add(i - 1);
//                indices.add(i);
//            }
        }
    }
}
