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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

public class MainWindowUI {

    public final int DEFAULT_WIDTH = 600;
    public final int DEFAULT_HEIGHT = 300;

    private final DatabaseManager databaseManager;
    private final ControlDirectory controlDirectory;

    private final MainWindowHeaderUI headerUI;
    private final MainWindowBodyUI bodyUI;
    private final MainWindowFooterUI footerUI;

    public MainWindowUI() {
        databaseManager = new DatabaseManager();
        controlDirectory = new ControlDirectory();

        headerUI = new MainWindowHeaderUI(controlDirectory);
        bodyUI = new MainWindowBodyUI(controlDirectory);
        footerUI = new MainWindowFooterUI(controlDirectory);
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
        createControls();

        Node header = headerUI.createNode();
        Node body = bodyUI.createNode();
        Node footer = footerUI.createNode();

        VBox layout = new VBox(header, body, footer);

        VBox.setVgrow(body, Priority.ALWAYS);

        return layout;
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
