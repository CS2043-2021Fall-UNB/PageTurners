package pageturners.ui.main;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import pageturners.controls.ControlDirectory;
import pageturners.ui.UIElement;

public class MainWindowHeaderUI extends UIElement {

    public static final int HEIGHT = 60;

    private final ControlDirectory controlDirectory;
    private final HBox layout;

    public MainWindowHeaderUI(ControlDirectory controlDirectory) {
        this.controlDirectory = controlDirectory;

        layout = new HBox();

        layout.setMinHeight(HEIGHT);
        layout.setMaxHeight(HEIGHT);

        //layout.setStyle("-fx-background-color: #FF0000;");
    }

    @Override
    public Node getNode() {
        return layout;
    }

    @Override
    public void update() {
        //layout.getChildren().addAll(elements);
    }
}
