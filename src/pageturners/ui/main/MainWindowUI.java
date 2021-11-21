package pageturners.ui.main;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

public class MainWindowUI {

    public final int DEFAULT_WIDTH = 600;
    public final int DEFAULT_HEIGHT = 300;

    private MainWindowHeaderUI _headerUI;
    private MainWindowBodyUI _bodyUI;
    private MainWindowFooterUI _footerUI;

    public MainWindowUI() {
        _headerUI = new MainWindowHeaderUI();
        _bodyUI = new MainWindowBodyUI();
        _footerUI = new MainWindowFooterUI();
    }

    public void setupStage(Stage stage) {
        Scene scene = getMainScene();
        
        stage.setTitle("Page Turners");
        stage.setScene(scene);

        stage.show();
    }

    private Scene getMainScene() {
        Parent layout = createMainLayout();

        Scene scene = new Scene(layout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        return scene;
    }

    private Parent createMainLayout() {
        Node header = _headerUI.createNode();
        Node body = _bodyUI.createNode();
        Node footer = _footerUI.createNode();

        VBox layout = new VBox(header, body, footer);

        VBox.setVgrow(body, Priority.ALWAYS);

        return layout;
    }
}
