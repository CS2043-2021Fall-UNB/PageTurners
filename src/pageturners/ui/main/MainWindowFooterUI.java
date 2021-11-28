package pageturners.ui.main;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import pageturners.controls.ControlDirectory;
import pageturners.ui.UIElement;

public class MainWindowFooterUI implements UIElement {

    public static final int HEIGHT = 40;

    private final ControlDirectory controlDirectory;

    public MainWindowFooterUI(ControlDirectory controlDirectory) {
        this.controlDirectory = controlDirectory;
    }

    @Override
    public Node createNode() {
        Node[] children = createChildren();

        HBox layout = new HBox(children);

        layout.setMinHeight(HEIGHT);
        layout.setMaxHeight(HEIGHT);

        //layout.setStyle("-fx-background-color: #0000FF;");

        return layout;
    }

    private Node[] createChildren() {
        return new Node[] {
            
        };
    }
    
}
