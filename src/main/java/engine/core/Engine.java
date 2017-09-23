package engine.core;

import engine.utils.Console;

class Engine {

    private static final long MS_PER_UPDATE = 10L;

    private Window window;
    private RenderManager renderManager;

    Engine() {
        renderManager = new RenderManager();
    }

    public void createWindow(int width, int height, String title, boolean resizable) {
        window = new Window(width, height, title, resizable, 0);
        window.create();
    }

    public void closeWindow() {
        window.close();
    }

    public void run() {
        init();
        loop();
        close();
    }

    public RenderManager getRenderManager() {
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

        while (!window.shouldClose()) {
            render = false;
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - prevTime;
            prevTime = currentTime;
            lag += elapsedTime;

            while (lag >= MS_PER_UPDATE) {
                render = true;
                renderManager.update();
                lag -= MS_PER_UPDATE;
            }

            if (render) {
                renderManager.render();
            } else {
                sync();
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
