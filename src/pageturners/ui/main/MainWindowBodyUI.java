package pageturners.ui.main;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import pageturners.ui.UIElement;

public class MainWindowBodyUI implements UIElement {

    public static final int MIN_HEIGHT = 200;

    @Override
    public Node createNode() {
        Node[] children = createChildren();

        HBox layout = new HBox(children);

        layout.setMinHeight(MIN_HEIGHT);
        
        //layout.setStyle("-fx-background-color: #00FF00;");

        return layout;
    }

    private Node[] createChildren() {
        return new Node[] {
            
        };
    }
    
}
