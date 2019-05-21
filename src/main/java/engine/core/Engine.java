package engine.core;

import engine.core.render.RenderManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Engine {

    private static final long MS_PER_UPDATE = 16L; //gives a bit more than 60FPS

    private Window window;
    private RenderManager renderManager;

    private final List<Runnable> tasks = Collections.synchronizedList(new ArrayList<>());

    Engine() {
        renderManager = new RenderManager();
    }

    Window createWindow(int width, int height, String title, boolean resizable) {
        window = new Window(width, height, title, resizable, 4);
        window.create();
        window.makeContextCurrent();
        return window;
    }

    void closeWindow() {
        window.close();
    }

    void run() {
        init();
        loop();
        close();
    }

    void runInGLThread(Runnable runnable) {
        tasks.add(runnable);
    }

    RenderManager getRenderManager() {
        return renderManager;
    }

    private void init() {
        renderManager.init();

//        new Console();//.initInput(window);
    }

    private void loop() {
        boolean render;
        long prevTime = System.currentTimeMillis();
        long lag = 0;
        long frameTimeBuffer = 0;
        int frames = 0;

        while (!window.shouldClose()) {
            if (tasks.size() > 0) {
                synchronized (tasks) {
                    tasks.forEach(Runnable::run);
                    tasks.clear();
                }
            }

            render = false;
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - prevTime;
            prevTime = currentTime;
            lag += elapsedTime;
            frameTimeBuffer += elapsedTime;

            while (lag >= MS_PER_UPDATE) {
                render = true;
                renderManager.update();
                lag -= MS_PER_UPDATE;

                if (frameTimeBuffer >= 1000L) {
                    window.setTitle("fractality FPS: " + frames);
                    frames = 0;
                    frameTimeBuffer = 0;
                }
            }

            if (render) {
                frames++;
                renderManager.render();
            } else {
//                sync();
            }

            window.update();
        }
    }

    private void close() {
        window.clean();
    }

    private void sync(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
