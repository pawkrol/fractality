package engine.render.shader;

import engine.utils.PathObtainer;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private int id;
    private int type;
    private String path;

    public Shader(String path, int type) {
        this.path = path;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int createAndCompile() throws Exception {
        String code = loadShaderCode(path);

        id = glCreateShader(type);
        if (id == GL_FALSE) {
            throw new Exception("Failed to create " + type + " shader");
        }

        glShaderSource(id, code);
        glCompileShader(id);
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new Exception("Failed to compile shader. Info: " + glGetShaderInfoLog(id));
        }

        return id;
    }

    public void clear() {
        glDeleteShader(id);
    }

    private String loadShaderCode(String source) throws Exception {
        Path filePath = PathObtainer.getProperPath(source);
        if (filePath == null) {
            throw new Exception("Can't find shader");
        }

        StringBuilder shaderCode = new StringBuilder();
        Files.lines(filePath).forEach(
                line -> shaderCode
                        .append(line)
                        .append(System.getProperty("line.separator"))
        );

        return shaderCode.toString();
    }
}
