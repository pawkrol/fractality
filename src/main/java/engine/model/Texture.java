package engine.model;

import engine.common.Image;

public class Texture {

    private final int id;

    public Texture(int id){
        this.id = id;
    }

    public Texture(String source, int id, Image.Type type){
//        super(source, type);

        this.id = id;
    }

    public int getId() {
        return id;
    }

}
