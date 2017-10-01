package engine.common;

import engine.utils.PathObtainer;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;

public class Image {

    public enum Type{
        TYPE_NOALPHA(3),
        TYPE_ALPHA(4);

        private final int value;
        Type(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private ByteBuffer image;
    private int width;
    private int height;
    private int comp;

    public Image(){}

    public Image(ByteBuffer buffer, int width, int height){
        this.image = buffer;
        this.width = width;
        this.height = height;
    }

    public Image(String source, Type type){
        source = PathObtainer.getProperPathString(source);

        try {
            load(source, type);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    public ByteBuffer getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getComp() {
        return comp;
    }

    public void clean(){
        stbi_image_free(image);
    }

    public void load(String source, Type type) throws RuntimeException{
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        image = stbi_load(source, w, h, comp, type.getValue());

        if (image == null){
            throw new RuntimeException("Failed to load image: " + stbi_failure_reason());
        }

        this.width = w.get(0);
        this.height = h.get(0);
        this.comp = comp.get(0);
    }
}
