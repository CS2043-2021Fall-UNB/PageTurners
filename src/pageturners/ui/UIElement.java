package pageturners.ui;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public abstract class UIElement {
    private Pane pane;

    protected UIElement() {
        pane = new Pane();
    }

    protected void show(Region region) {
        pane.getChildren().setAll(region);
        region.prefWidthProperty().bind(pane.widthProperty());
        region.prefHeightProperty().bind(pane.heightProperty());
    }
    
    public Region getNode() {
        return pane;
    }
}