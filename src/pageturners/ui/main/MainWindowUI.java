package pageturners.ui.main;

import javafx.stage.Stage;
import pageturners.controls.*;
import pageturners.database.DatabaseManager;
import pageturners.ui.UIElement;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

public class MainWindowUI extends UIElement {

    public final int DEFAULT_WIDTH = 600;
    public final int DEFAULT_HEIGHT = 300;

    private final DatabaseManager databaseManager;
    private final ControlDirectory controlDirectory;

    private MainWindowHeaderUI headerUI;
    private MainWindowBodyUI bodyUI;
    private MainWindowFooterUI footerUI;

    public MainWindowUI() {
        databaseManager = new DatabaseManager();
        controlDirectory = new ControlDirectory();
        
        createControls();

        headerUI = new MainWindowHeaderUI(this, controlDirectory);
        bodyUI = new MainWindowBodyUI(controlDirectory);
        footerUI = new MainWindowFooterUI(controlDirectory);

        display();
    }

    private void display() {
        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(5);

        Region header = headerUI.getNode();
        Region body = bodyUI.getNode();
        Region footer = footerUI.getNode();

        Separator separator = new Separator(Orientation.HORIZONTAL);
        Separator separator2 = new Separator(Orientation.HORIZONTAL);

        layout.getChildren().clear();
        layout.getChildren().addAll(separator, header, separator2, body, footer);

        double headerHeight = 25;
        header.setMinHeight(headerHeight);
        header.setPrefHeight(headerHeight);
        header.setMaxHeight(headerHeight);

        VBox.setVgrow(body, Priority.ALWAYS);

        show(layout);
    }

    public MainWindowHeaderUI getHeader() {
        return headerUI;
    }

    public MainWindowBodyUI getBody() {
        return bodyUI;
    }

    public MainWindowFooterUI getFooter() {
        return footerUI;
    }

    public void setupStage(Stage stage) {
        Scene scene = getMainScene();
        
        stage.setTitle("Page Turners");
        stage.setScene(scene);
        
        stage.show();
    }

    private Scene getMainScene() {
        Scene scene = new Scene(getNode(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        return scene;
    }

    private void createControls() {
        // clear controls to ensure directory is empty before adding
        controlDirectory.clearControls();

        // create login control first because its needed by other controls
        LoginControl loginControl = new LoginControl(databaseManager);
        controlDirectory.addControl(loginControl);

        // create and add all controls to the control directory
        controlDirectory.addControl(new AddUserCommentControl(loginControl, databaseManager));
        controlDirectory.addControl(new AddUserPostControl(loginControl, databaseManager));
        controlDirectory.addControl(new ChangeUserMuteStatusControl(loginControl, databaseManager));
        controlDirectory.addControl(new CreateAccountControl(databaseManager));
        controlDirectory.addControl(new DeleteAccountControl(loginControl, databaseManager));
        controlDirectory.addControl(new DeleteCommentControl(loginControl, databaseManager));
        controlDirectory.addControl(new DeleteUserAccountControl(loginControl, databaseManager));
        controlDirectory.addControl(new DeleteUserCommentControl(loginControl, databaseManager));
        controlDirectory.addControl(new DeleteUserPostControl(loginControl, databaseManager));
        controlDirectory.addControl(new ManageModeratorControl(loginControl, databaseManager));
        controlDirectory.addControl(new SearchPostsControl(databaseManager));
        controlDirectory.addControl(new ViewCategoryControl(databaseManager));
        controlDirectory.addControl(new ViewPostControl(databaseManager));
    }
}
