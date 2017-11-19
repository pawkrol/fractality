package modules.configs;

import engine.core.render.FrameConfig;

import static org.lwjgl.opengl.GL11.*;

public class ClearFrame implements FrameConfig {

    private float r;
    private float g;
    private float b;
    private float a;

    public ClearFrame() {}

    public ClearFrame(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public void initFrame() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(r, g, b, a);
    }
}
