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
            for (int x = 1; x < 3; x++) {
                for (int y = 1; y < 3; y++) {
                    for (int z = 1; z < 3; z++) {
                        int sum = Math.abs(x) + Math.abs(y) + Math.abs(z);
                        if (x % 2 == 0) {
                            System.out.println( (translation.x + x) + ", " + (translation.y + y) + ", " + (translation.z + z));
                            newTranslations.add(
                                    new Vector3f(translation.x + x * 3,
                                            translation.y + y * 3,
                                            translation.z + z * 3)
                            );
                        }
                    }
                }
            }
        }
        translations.clear();
        translations.addAll(newTranslations);

        System.out.println(newTranslations.size() * 12);
        box.getMesh().setInstances(newTranslations.size());
        box.getMesh().setInstanceData(newTranslations);
    }

    public void addSelfToScene(Scene scene) {
//        ((Transform) scene.getScenegraph().getRoot())
//                .getScale().set(currentScale, currentScale, currentScale);
    }

}
