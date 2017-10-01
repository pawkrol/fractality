package modules;

import engine.render.RenderConfig;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.glCullFace;

public class EnableCulling implements RenderConfig {
    @Override
    public void enable() {
        glCullFace(GL_BACK);
    }
}
