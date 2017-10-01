package modules;

import engine.render.FrameConfig;

import static org.lwjgl.opengl.GL11.*;

public class ClearFrame implements FrameConfig {
    @Override
    public void initFrame() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(.15f, .15f, .15f, 0.f);
    }
}
