package engine.scene;

import engine.component.Component;

import java.util.HashMap;
import java.util.Map;

public class GameObject extends Node {

    private Map<Component.Type, Component> components;
    private Scene scene;

    public GameObject(Scene scene) {
        this.components = new HashMap<>();
        this.scene = scene;
    }

    @Override
    public void update() {
        components.get(Component.Type.TRANSFORM).execute();

        super.update();
    }

    @Override
    public void render() {
        super.render();

        components.get(Component.Type.RENDER).execute();
    }

    public void addComponent(Component.Type type, Component component) {
        component.setOwner(this);
        components.put(type, component);
    }

    public void removeComponent(Component.Type type) {
        components.remove(type);
    }

    public Scene getScene() {
        return scene;
    }

}
