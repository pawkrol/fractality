package engine.model;

import engine.common.Image;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL21.GL_SRGB;
import static org.lwjgl.opengl.GL21.GL_SRGB_ALPHA;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.opengl.GL45.glCreateTextures;

public class TextureLoader {

    private static Map<String, Texture> textures = new HashMap<>();

    public static Texture load(String source, Image.Type type){
        if (textures.containsKey(source)) {
            return textures.get(source);
        }

        int textureID = glCreateTextures(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);
        Image image = new Image(source, type);

        if (type == Image.Type.TYPE_ALPHA) {
            glTexImage2D(GL_TEXTURE_2D,
                    0,
                    GL_SRGB_ALPHA,
                    image.getWidth(),
                    image.getHeight(), 
                    0,
                    GL_RGBA,
                    GL_UNSIGNED_BYTE,
                    image.getImage());
        } else if (type == Image.Type.TYPE_NOALPHA) {
            glTexImage2D(GL_TEXTURE_2D,
                    0,
                    GL_SRGB,
                    image.getWidth(),
                    image.getHeight(),
                    0,
                    GL_RGB,
                    GL_UNSIGNED_BYTE,
                    image.getImage());
        }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glGenerateMipmap(GL_TEXTURE_2D);

        Texture texture = new Texture(textureID);
        textures.put(source, texture);

        return texture;
    }

}
