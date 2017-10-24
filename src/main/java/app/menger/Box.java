package app.menger;

import engine.model.*;
import engine.scene.GameObject;
import engine.scene.Scene;
import modules.loaders.obj.OBJLoader;
import org.joml.Vector3f;

import java.util.ArrayList;

public class Box extends GameObject {

    private Mesh mesh;

    public Box(Scene scene, MengerShader mengerShader) {
        super(scene, mengerShader);
        init();
    }

    @Override
    public void update() {
    }

    private void init() {
        Model model = new Model();
        mesh = createMesh();
        model.setMesh(mesh);
        setModel(model);
    }

    private Mesh createMesh() {
        ArrayList<Vector3f> offsets = new ArrayList<>();
        offsets.add(new Vector3f(0, 0, 0));

        Mesh mesh = OBJLoader.load("objects/box.obj");
        mesh.setInstanced(true);
        mesh.setInstances(1);
        mesh.setInstanceData(offsets);

        return mesh;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}
