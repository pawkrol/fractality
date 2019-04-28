package engine.core;

import engine.input.KeyboardInput;
import engine.input.MouseClickInput;
import engine.input.MousePositionInput;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    public int width;
    public int height;

    private long handle;
    private int aaLevel;
    private boolean resizable;
    private String title;

    Window(int width, int height, String title, boolean resizable, int aaLevel) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.resizable = resizable;
        this.aaLevel = aaLevel;
    }

    public void create() {
        try {
            init();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void close() {
        glfwSetWindowShouldClose(handle, true);
    }

    public void clean() {
        glfwFreeCallbacks(handle);
        glfwDestroyWindow(handle);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    public void update() {
        glfwSwapBuffers(handle);
        glfwPollEvents();
    }

    public void setTitle(String title) {
        glfwSetWindowTitle(handle, title);
    }

    public void makeContextCurrent() {
        glfwMakeContextCurrent(handle);
        GL.createCapabilities();
        glfwSwapInterval(0);
    }

    private void init() throws RuntimeException {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 4);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
        if (aaLevel > 0) {
            glfwWindowHint(GLFW_SAMPLES, aaLevel);
        }

        handle = glfwCreateWindow(width, height, title, NULL, NULL);
        if (handle == NULL){
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetInputMode(handle, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwSetKeyCallback(handle, KeyboardInput.getInstance());
        glfwSetCursorPosCallback(handle, MousePositionInput.getInstance());
        glfwSetMouseButtonCallback(handle, MouseClickInput.getInstance());

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(handle, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);
    }

    public void showWindow() {
        glfwShowWindow(handle);
    }

}
