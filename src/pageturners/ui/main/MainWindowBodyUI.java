package pageturners.ui.main;

import javafx.scene.Node;
import pageturners.controls.ControlDirectory;
import pageturners.controls.ViewCategoryControl;
import pageturners.ui.UIElement;
import pageturners.ui.modules.ViewCategoryUI;

public class MainWindowBodyUI extends UIElement {

    public static final int MIN_HEIGHT = 200;

    private final ControlDirectory controlDirectory;
    private final ViewCategoryUI viewCategoryUI;

    public MainWindowBodyUI(ControlDirectory controlDirectory) {
        this.controlDirectory = controlDirectory;
        this.viewCategoryUI = new ViewCategoryUI((ViewCategoryControl)controlDirectory.getControl(ViewCategoryControl.class));
    }

    @Override
    public Node getNode() {
        return viewCategoryUI.displayCategories();
    }

    @Override
    public void update() {
    }
}
