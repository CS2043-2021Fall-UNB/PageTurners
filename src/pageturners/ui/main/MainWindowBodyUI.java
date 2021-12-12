package pageturners.ui.main;

import java.text.DateFormat;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import pageturners.controls.ControlDirectory;
import pageturners.controls.CreateAccountControl;
import pageturners.controls.LoginControl;
import pageturners.controls.ViewCategoryControl;
import pageturners.models.AdminObject;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;
import pageturners.ui.modules.CreateAccountUI;
import pageturners.ui.modules.HomePageUI;
import pageturners.ui.modules.LoginUI;
import pageturners.ui.modules.ViewCategoryUI;

public class MainWindowBodyUI extends UIElement {

    private static final DateFormat ACCOUNT_CREATED_DATE_FORMAT = DateFormat.getDateTimeInstance();

    private final ControlDirectory controlDirectory;
    private final LoginControl loginControl;

    public MainWindowBodyUI(ControlDirectory controlDirectory) {
        this.controlDirectory = controlDirectory;

        loginControl = (LoginControl)controlDirectory.getControl(LoginControl.class);
        loginControl.registerUserLoginCallback(user -> displayUserPanel());
        loginControl.registerAdminLoginCallback(admin -> displayAdminPanel());
        
        displayHomePage();
    }

    public void displayHomePage() {
        HomePageUI homePageUI = new HomePageUI();

        show(homePageUI.getNode());
    }

    public void displayCategories() {
        ViewCategoryUI viewCategoryUI = new ViewCategoryUI(controlDirectory, this);

        show(viewCategoryUI.getNode());
    }

    public void displayLoginRegister() {
        HBox layout = new HBox();

        LoginUI loginUI = new LoginUI((LoginControl)controlDirectory.getControl(LoginControl.class));
        CreateAccountUI createAccountUI = new CreateAccountUI((CreateAccountControl)controlDirectory.getControl(CreateAccountControl.class));

        Region loginNode = loginUI.getNode();
        Region createAccountNode = createAccountUI.getNode();

        Separator separator = new Separator(Orientation.VERTICAL);

        layout.getChildren().setAll(loginNode, separator, createAccountNode);
        layout.setSpacing(10);
        
        HBox.setHgrow(loginNode, Priority.ALWAYS);
        HBox.setHgrow(createAccountNode, Priority.ALWAYS);

        show(layout);
    }

    public void displayUserPanel() {
        UserObject user = loginControl.getUserObject();

        if (user == null) {
            displayLoginRegister();
            return;
        }

        GridPane layout = new GridPane();

        layout.setHgap(15);
        layout.setVgap(5);

        Label header = new Label("User Panel - Welcome " + user.username + "!");
        header.setFont(Font.font(30));

        Label usernameLabel = new Label("Username:");
        Label usernameText = new Label(user.username);

        Label accountCreatedLabel = new Label("Account Created:");
        Label accountCreatedText = new Label(ACCOUNT_CREATED_DATE_FORMAT.format(user.accountCreated));

        Label moderatorStatusLabel = new Label("Is Moderator?");
        Label moderatorStatusText = new Label(user.isMod ? "Yes" : "No");

        Label muteStatusLabel = new Label("Is Muted?");
        Label muteStatusText = new Label(user.isMuted ? "Yes" : "No");

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(event -> loginControl.saveUserObject(null));

        layout.add(header, 0, 0, 5, 1);

        layout.add(usernameLabel, 0, 1);
        layout.add(usernameText, 1, 1);
        layout.add(accountCreatedLabel, 0, 2);
        layout.add(accountCreatedText, 1, 2);
        layout.add(moderatorStatusLabel, 3, 1);
        layout.add(moderatorStatusText, 4, 1);
        layout.add(muteStatusLabel, 3, 2);
        layout.add(muteStatusText, 4, 2);
        layout.add(logoutButton, 0, 3, 5, 1);
        layout.add(new Separator(Orientation.VERTICAL), 2, 1, 1, 2);

        layout.setMaxWidth(Double.MAX_VALUE);
        logoutButton.setMaxWidth(Double.MAX_VALUE);

        GridPane.setHgrow(usernameText, Priority.ALWAYS);
        GridPane.setHgrow(accountCreatedText, Priority.ALWAYS);
        GridPane.setHgrow(moderatorStatusText, Priority.ALWAYS);
        GridPane.setHgrow(muteStatusText, Priority.ALWAYS);
        GridPane.setHgrow(logoutButton, Priority.ALWAYS);

        show(layout);
    }

    public void displayAdminPanel() {
        AdminObject admin = loginControl.getAdminObject();

        if (admin == null) {
            displayLoginRegister();
            return;
        }

        GridPane layout = new GridPane();

        layout.setHgap(15);
        layout.setVgap(5);

        Label header = new Label("Admin Panel - Welcome " + admin.username + "!");
        header.setFont(Font.font(30));

        Label usernameLabel = new Label("Username");
        Label usernameText = new Label(admin.username);

        Label accountCreatedLabel = new Label("Account Created");
        Label accountCreatedText = new Label(ACCOUNT_CREATED_DATE_FORMAT.format(admin.accountCreated));

        layout.add(header, 0, 0, 2, 1);
        layout.add(usernameLabel, 0, 1);
        layout.add(usernameText, 1, 1);
        layout.add(accountCreatedLabel, 0, 2);
        layout.add(accountCreatedText, 1, 2);

        show(layout);
    }
}
