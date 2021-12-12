package pageturners.ui.main;

import javafx.scene.layout.HBox;
import pageturners.controls.ControlDirectory;
import pageturners.ui.UIElement;

public class MainWindowFooterUI extends UIElement {

    public static final int HEIGHT = 40;

    private final ControlDirectory controlDirectory;

    public MainWindowFooterUI(ControlDirectory controlDirectory) {
        this.controlDirectory = controlDirectory;

    }

    private void display() {
        HBox layout = new HBox();

        //layout.setMinHeight(HEIGHT);
        //layout.setMaxHeight(HEIGHT);
        //layout.setStyle("-fx-background-color: #0000FF;");

        show(layout);
    }
}
