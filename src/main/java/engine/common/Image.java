package engine.common;

import engine.utils.PathObtainer;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import org.lwjgl.BufferUtils;

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

    public static WritableImage encode(ByteBuffer buffer, int width, int height) {
        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {

                int i = (x + (width * y)) * 3;
                int r = buffer.get(i) & 0xFF;
                int g = buffer.get(i + 1) & 0xFF;
                int b = buffer.get(i + 2) & 0xFF;
                pixelWriter.setArgb(x, (height - (y + 1)), b | g << 8 | r << 16);
            }
        }

        return image;
    }
}
