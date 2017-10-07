package modules.loaders.obj;

import engine.model.Mesh;
import engine.utils.PathObtainer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class OBJLoader {

    public static Mesh load(String source) {
        Path file = PathObtainer.getProperPath(source);
        assert file != null;

        Mesh mesh = null;
        try (Stream<String> objStream = Files.lines(file)) {
            mesh = readObj(objStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mesh;
    }

    private static Mesh readObj(Stream<String> objStream) {
        OBJHelper objHelper = new OBJHelper();

        objStream.forEach(line -> {
            String[] tokens = line.split("\\s+");

            switch (tokens[0]) {
                case "v":
                    objHelper.parseVertex(tokens[1], tokens[2], tokens[3]);
                    break;
                case "vn":
                    objHelper.parseNormal(tokens[1], tokens[2], tokens[3]);
                    break;
                case "vt":
                    objHelper.parseTexCoord(tokens[1], tokens[2]);
                    break;
                case "f":
                    objHelper.readFace(tokens[1], tokens[2], tokens[3]);
                    break;
            }
        });

        return objHelper.process();
    }

}
