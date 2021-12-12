package pageturners.ui.main;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import pageturners.controls.ControlDirectory;
import pageturners.ui.UIElement;

public class MainWindowFooterUI extends UIElement {

    public static final int HEIGHT = 40;

    private final ControlDirectory controlDirectory;
    private final HBox layout;

    public MainWindowFooterUI(ControlDirectory controlDirectory) {
        this.controlDirectory = controlDirectory;
        layout = new HBox();

        layout.setMinHeight(HEIGHT);
        layout.setMaxHeight(HEIGHT);

        //layout.setStyle("-fx-background-color: #0000FF;");
    }

    @Override
    public Node getNode() {
        return layout;
    }

    @Override
    public void update() {
    }
    
}
