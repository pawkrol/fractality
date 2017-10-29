package app.menger;

import engine.scene.Node;
import engine.scene.Scene;
import engine.scene.Transform;

public class LeeuwenbergTree {

    private Scene scene;
    private Node initNode;
    private MengerShader mengerShader;

    LeeuwenbergTree(Scene scene, MengerShader mengerShader) {
         this.scene = scene;
         this.mengerShader = mengerShader;

         init();
         evolve();
    }

    public void evolve() {
        growOn(initNode, 0,0.02f, 0.015f);
    }

    private void growOn(Node node, int iterations, float bottomR, float topR) {
        iterations++;
        if (iterations > 10) return;

        Cylinder cylinder = new Cylinder(scene, mengerShader);
        cylinder.generate(topR, bottomR, 0.5f);

        Transform transform = new Transform();
        transform.getTranslation().set(0, 0.48f, 0);
        transform.getScale().mul(0.83f);
        transform.getRotation().rotateY( (float)Math.toRadians(40) );
        transform.getRotation().rotateX( (float)Math.toRadians(40) );
        transform.getRotation().rotateY( (float)Math.toRadians(80) );

        Node lNode = node.addChildren(transform)
                         .addChildren(cylinder);

        Cylinder cylinder2 = new Cylinder(scene, mengerShader);
        cylinder2.generate(topR, bottomR, 0.5f);

        Transform transform2 = new Transform();
        transform2.getTranslation().set(0, 0.49f, 0);
        transform2.getScale().mul(0.83f);
        transform2.getRotation().rotateY( (float)Math.toRadians(215) );
        transform2.getRotation().rotateX( (float)Math.toRadians(25) );
        transform2.getRotation().rotateY( (float)Math.toRadians(80) );

        Node rNode = node.addChildren(transform2)
                         .addChildren(cylinder2);

        bottomR = getBootomR(bottomR, topR, 0.49f, 0.5f);
        topR = getTopR(bottomR);
        growOn(rNode, iterations, bottomR, topR);
        growOn(lNode, iterations, bottomR, topR);
    }

    private float getBootomR(float prevBottomR, float prevTopR, float move, float height) {
        float buff = (prevBottomR - prevTopR) * move;
        return (prevBottomR - (buff / height));
    }

    private float getTopR(float newBottomR) {
        return 0.85f * newBottomR;
    }

    private void init() {
        Cylinder cylinder = new Cylinder(scene, mengerShader);
        cylinder.generate(0.015f, 0.03f, 0.5f);

        initNode = scene.getScenegraph()
                .getRoot()
                .addChildren(cylinder);
    }
}
