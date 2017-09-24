package app;

import engine.event.Event;
import engine.event.EventBus;
import engine.event.EventObserver;
import engine.model.Mesh;
import engine.model.Model;
import engine.model.Vertex;
import engine.scene.GameObject;
import engine.scene.Scene;
import org.joml.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_B;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;

public class Triangle extends GameObject implements EventObserver{

    private Vector3f color;

    public Triangle(Scene scene, TestShaderProgram testShaderProgram) {
        super(scene, testShaderProgram);
        EventBus.getInstance().attach(this);

        init();
    }

    @Override
    public void update() {
        getShaderProgram().setColor(color);
    }

    private void init() {
        Model model = new Model();
        model.setMesh( createMesh() );
        setModel(model);

        color = new Vector3f(1, 1, 1);
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

    @Override
    public void receiveEvent(int event, Object... params) {
        if (event == Event.KEY_RELEASED) {
            if ( (int) params[0] == GLFW_KEY_R ) {
                color = new Vector3f(1, 0, 0);
            } else if ( (int) params[0] == GLFW_KEY_G ) {
                color = new Vector3f(0, 1, 0);
            } else if ( (int) params[0] == GLFW_KEY_B ) {
                color = new Vector3f(0, 0, 1);
            }
        }
    }
}
