package app.menger;

import engine.model.Mesh;
import engine.model.Model;
import engine.scene.GameObject;
import engine.scene.Scene;
import modules.loaders.obj.OBJLoader;
import org.joml.Vector3f;

public class Plane extends GameObject {

    private MengerShader mengerShader;
    private Vector3f color;

    public Plane(Scene scene, MengerShader mengerShader) {
        super(scene, mengerShader);

        this.mengerShader = mengerShader;
        this.color = new Vector3f(0.22f, 0.47f, 0.08f);

        init();
    }

    @Override
    public void updateUniforms() {
        mengerShader.setColor(color);
    }

    private void init() {
        Model model = new Model();
        model.setMesh( getMesh() );
        setModel(model);
    }

    private Mesh getMesh() {
        return OBJLoader.load("objects/plane.obj");
    }
}
