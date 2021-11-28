package pageturners.ui.main;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import pageturners.controls.ControlDirectory;
import pageturners.ui.UIElement;

public class MainWindowHeaderUI implements UIElement {

    public static final int HEIGHT = 60;

    private final ControlDirectory controlDirectory;

    public MainWindowHeaderUI(ControlDirectory controlDirectory) {
        this.controlDirectory = controlDirectory;
    }

    @Override
    public Node createNode() {
        Node[] children = createChildren();

        HBox layout = new HBox(children);

        layout.setMinHeight(HEIGHT);
        layout.setMaxHeight(HEIGHT);

        //layout.setStyle("-fx-background-color: #FF0000;");

        return layout;
    }

    private Node[] createChildren() {
        return new Node[] {
            
        };
    }
}
