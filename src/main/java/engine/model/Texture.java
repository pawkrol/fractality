package engine.model;

import static org.lwjgl.opengl.GL45.glBindTextureUnit;

public class Texture {

    private final int id;

    public Texture(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void bind() {
        glBindTextureUnit(0, id);
    }

    public void unbind() {
        glBindTextureUnit(0, 0);
    }
}
