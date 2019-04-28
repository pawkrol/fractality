package engine.model;

import java.util.Optional;

public class Model {

    private Mesh mesh;
    private Material material;

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public boolean hasMaterial() {
        return material != null;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void bind() {
        Optional.ofNullable(material)
                .ifPresent(Material::bind);
    }

    public void draw() {
        mesh.draw();
    }
}
