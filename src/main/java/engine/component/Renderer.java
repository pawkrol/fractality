package engine.component;

public class Renderer extends Component {

    private RenderConfig renderConfig;

    public Renderer(RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
    }

    @Override
    public void execute() {
        renderConfig.enable();



        renderConfig.disable();
    }
}
