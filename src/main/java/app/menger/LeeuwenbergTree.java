package app.menger;

import engine.scene.Node;
import engine.scene.Scene;
import engine.scene.Transform;

public class LeeuwenbergTree {

    private MengerShader mengerShader;
    private Scene scene;
    private Node lastNode;

    LeeuwenbergTree(Scene scene) {
         this.scene = scene;

         mengerShader = new MengerShader(1200, 800);
         mengerShader.create();

         init();
    }

    public void evolve() {
        Cylinder cylinder = new Cylinder(scene, mengerShader);
        cylinder.generate(0.015f, 0.02f, 0.5f);

        Transform transform = new Transform();
        transform.getTranslation().set(0, 0.48f, 0);
        transform.getScale().mul(0.83f);
        transform.getRotation().rotateY( (float)Math.toRadians(40) );
        transform.getRotation().rotateX( (float)Math.toRadians(40) );
        transform.getRotation().rotateY( (float)Math.toRadians(80) );

        lastNode = scene.getScenegraph()
                .getRoot()
                .addChildren(transform)
                .addChildren(cylinder);

        Cylinder cylinder2 = new Cylinder(scene, mengerShader);
        cylinder2.generate(0.015f, 0.02f, 0.5f);

        Transform transform2 = new Transform();
        transform2.getTranslation().set(0, 0.448f, 0);
        transform2.getScale().mul(0.83f);
        transform2.getRotation().rotateY( (float)Math.toRadians(215) );
        transform2.getRotation().rotateX( (float)Math.toRadians(25) );
        transform2.getRotation().rotateY( (float)Math.toRadians(80) );

        lastNode
                .addChildren(transform2)
                .addChildren(cylinder2);
    }

    private void init() {
        Cylinder cylinder = new Cylinder(scene, mengerShader);
        cylinder.generate(0.015f, 0.02f, 0.5f);

        scene.getScenegraph()
                .getRoot()
                .addChildren(cylinder);
    }
}
