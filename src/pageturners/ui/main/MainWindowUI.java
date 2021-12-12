package pageturners.ui.main;

import javafx.stage.Stage;
import pageturners.controls.AddUserCommentControl;
import pageturners.controls.AddUserPostControl;
import pageturners.controls.ChangeUserMuteStatusControl;
import pageturners.controls.ControlDirectory;
import pageturners.controls.CreateAccountControl;
import pageturners.controls.DeleteAccountControl;
import pageturners.controls.DeleteCommentControl;
import pageturners.controls.DeleteUserAccountControl;
import pageturners.controls.DeleteUserCommentControl;
import pageturners.controls.DeleteUserPostControl;
import pageturners.controls.LoginControl;
import pageturners.controls.ManageModeratorControl;
import pageturners.controls.SearchPostsControl;
import pageturners.controls.ViewCategoryControl;
import pageturners.controls.ViewPostControl;
import pageturners.database.DatabaseManager;
import pageturners.ui.UIElement;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

public class MainWindowUI extends UIElement {

    public final int DEFAULT_WIDTH = 600;
    public final int DEFAULT_HEIGHT = 300;

    private final DatabaseManager databaseManager;
    private final ControlDirectory controlDirectory;

    private final VBox layout;

    private MainWindowHeaderUI headerUI;
    private MainWindowBodyUI bodyUI;
    private MainWindowFooterUI footerUI;

    public MainWindowUI() {
        databaseManager = new DatabaseManager();
        controlDirectory = new ControlDirectory();
        
        createControls();

        layout = new VBox();

        headerUI = new MainWindowHeaderUI(controlDirectory);
        bodyUI = new MainWindowBodyUI(controlDirectory);
        footerUI = new MainWindowFooterUI(controlDirectory);
    }

    @Override
    public void update() {   
        Node header = headerUI.getNode();
        Node body = bodyUI.getNode();
        Node footer = footerUI.getNode();

        layout.getChildren().clear();
        layout.getChildren().addAll(header, body, footer);

        VBox.setVgrow(body, Priority.ALWAYS);     
    }

    @Override
    public Node getNode() {
        return layout;
    }

    public void setupStage(Stage stage) {
        Scene scene = getMainScene();
        
        stage.setTitle("Page Turners");
        stage.setScene(scene);

        stage.show();
    }

    private Scene getMainScene() {
        update();

        Scene scene = new Scene((Parent)getNode(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
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
