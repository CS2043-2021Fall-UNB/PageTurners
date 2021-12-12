package pageturners.ui;

import javafx.scene.Node;

public abstract class UIElement {
    protected UIElement parent;

    public void setParent(UIElement parent) {
        this.parent = parent;
    }

    public abstract void update();
    
    public abstract Node getNode();

    protected void updateParent() {
        if (parent != null) {
            parent.update();
        }
    }
}