package modules.configs;

import engine.core.render.RenderConfig;

import static org.lwjgl.opengl.GL11.*;

public class EnableCulling implements RenderConfig {
    @Override
    public void enable() {
        glEnable(GL_DEPTH_TEST);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
    }
}
