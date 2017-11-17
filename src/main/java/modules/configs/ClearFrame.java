package modules.configs;

import engine.render.FrameConfig;

import static org.lwjgl.opengl.GL11.*;

public class ClearFrame implements FrameConfig {
    @Override
    public void initFrame() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(.35f, .77f, .97f, 0.f);
//        glClearColor(1f, 1f, 1f, 0.f);
//        glClearColor(0.1f, 0.1f, 0.1f, 0.f);
    }
}
