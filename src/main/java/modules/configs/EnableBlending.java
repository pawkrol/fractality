package modules.configs;

import engine.core.render.RenderConfig;

import static org.lwjgl.opengl.GL11.*;

public class EnableBlending implements RenderConfig {
    @Override
    public void enable() {
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
}
