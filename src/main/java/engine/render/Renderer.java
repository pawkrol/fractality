package engine.render;

public class Renderer {

    private RenderConfig renderConfig;

    public Renderer(RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
    }

    public void execute() {
        renderConfig.enable();

        renderConfig.disable();
    }

}
