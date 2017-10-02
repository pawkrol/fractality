package app.menger;

import engine.scene.Scene;
import engine.scene.Transform;
import org.joml.Vector3f;

import java.util.ArrayList;

public class MengerSponge {

    private Box box;
    private ArrayList<Vector3f> translations;
    private float currentScale = 1;

    public MengerSponge(Box box) {
        this.box = box;
        translations = new ArrayList<>();

        translations.add(new Vector3f());
    }

    public void evolve() {
        currentScale /= 3.f;

        ArrayList<Vector3f> newTranslations = new ArrayList<>();
        for (Vector3f translation: translations) {
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    for (int z = -1; z < 2; z++) {
                        newTranslations.add(
                                new Vector3f(translation.x + x * 2 * currentScale,
                                             translation.y + y * 2 * currentScale,
                                             translation.z + z * 2 * currentScale)
                        );
                    }
                }
            }
        }
        translations.clear();
        translations.addAll(newTranslations);
    }

    public void addSelfToScene(Scene scene) {
        for (Vector3f translation: translations) {
            Transform transform = new Transform();
            transform.getScale().mul(currentScale);
            transform.getTranslation().set(translation);

            scene.getScenegraph()
                    .getRoot()
                    .addChildren(transform)
                    .addChildren(box);
        }
    }

}
