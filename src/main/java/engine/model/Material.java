package engine.model;

public class Material {

    private Texture texture;

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void bind() {
        texture.bind();
    }

    public void unbind() {
        texture.unbind();
    }
}
