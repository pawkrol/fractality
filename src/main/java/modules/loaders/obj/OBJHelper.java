package modules.loaders.obj;

import engine.model.Mesh;
import engine.model.Vertex;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;

class OBJHelper {

    private ArrayList<Vertex> vertices;
    private ArrayList<Vector3f> normals;
    private ArrayList<Vector2f> texCoords;
    private ArrayList<Face> faces;

    OBJHelper() {
        vertices = new ArrayList<>();
        normals = new ArrayList<>();
        texCoords = new ArrayList<>();
        faces = new ArrayList<>();
    }

    void parseVertex(String val1, String val2, String val3) {
        float x = Float.parseFloat(val1);
        float y = Float.parseFloat(val2);
        float z = Float.parseFloat(val3);

        vertices.add(
                new Vertex(new Vector3f(x, y, z))
        );
    }

    void parseNormal(String val1, String val2, String val3) {
        float x = Float.parseFloat(val1);
        float y = Float.parseFloat(val2);
        float z = Float.parseFloat(val3);

        normals.add(new Vector3f(x, y, z));
    }

    void parseTexCoord(String val1, String val2) {
        float u = Float.parseFloat(val1);
        float v = 1f - Float.parseFloat(val2);

        texCoords.add(new Vector2f(u, v));
    }

    void readFace(String idc1, String idc2, String idc3) {
        String[] ig1 = idc1.split("/");
        String[] ig2 = idc2.split("/");
        String[] ig3 = idc3.split("/");

        Face face = new Face();
        face.setIndexGroup1(parseIndexGroup(ig1));
        face.setIndexGroup2(parseIndexGroup(ig2));
        face.setIndexGroup3(parseIndexGroup(ig3));

        faces.add(face);
    }

    Mesh process() {
        ArrayList<Integer> indices = new ArrayList<>();

        for (Face face: faces) {
            processIndexGroup(face.getIndexGroup1(), indices);
            processIndexGroup(face.getIndexGroup2(), indices);
            processIndexGroup(face.getIndexGroup3(), indices);
        }

        return new Mesh(vertices, indices);
    }

    private void processIndexGroup(IndexGroup indexGroup, ArrayList<Integer> indices) {
        int vi = indexGroup.getVi();
        int vti = indexGroup.getVti();
        int vni = indexGroup.getVni();

        if (!normals.isEmpty()) {
            Vector3f vn = normals.get(vni);
            vertices.get(vi).setNormal(vn);
        }

        if (!texCoords.isEmpty()) {
            Vector2f vt = texCoords.get(vti);
            vertices.get(vi).setTextureCoord(vt);//TODO: detect more than one tex coord at a vertex
        }

        indices.add(vi);
    }

    private IndexGroup parseIndexGroup(String[] ig) {
        int vi = Integer.parseInt(ig[0]);
        int vti = ig[1].isEmpty()? 0 : Integer.parseInt(ig[1]);
        int vni = ig[2].isEmpty()? 0 : Integer.parseInt(ig[2]);

        return new IndexGroup(vi - 1, vti - 1, vni - 1);
    }

}
