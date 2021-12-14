package pageturners.ui.main;

import java.text.DateFormat;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pageturners.controls.ControlDirectory;
import pageturners.controls.CreateAccountControl;
import pageturners.controls.LoginControl;
import pageturners.database.DatabaseManager;
import pageturners.models.AdminObject;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;
import pageturners.ui.modules.AddUserPostUI;
import pageturners.ui.modules.ChangeUserMuteStatusUI;
import pageturners.ui.modules.CreateAccountUI;
import pageturners.ui.modules.DeleteAccountUI;
import pageturners.ui.modules.HomePageUI;
import pageturners.ui.modules.LoginUI;
import pageturners.ui.modules.ManageModeratorUI;
import pageturners.ui.modules.SearchPostsUI;
import pageturners.ui.modules.ViewCategoryUI;
import pageturners.ui.modules.ViewPostUI;

public class MainWindowBodyUI extends UIElement {

    private static final DateFormat ACCOUNT_CREATED_DATE_FORMAT = DateFormat.getDateTimeInstance();

    private final ControlDirectory controlDirectory;
    private final DatabaseManager databaseManager;
    private final LoginControl loginControl;

    public MainWindowBodyUI(ControlDirectory controlDirectory, DatabaseManager databaseManager) {
        this.controlDirectory = controlDirectory;
        this.databaseManager = databaseManager;

        loginControl = (LoginControl)controlDirectory.getControl(LoginControl.class);
        loginControl.registerUserLoginCallback(user -> displayUserPanel());
        loginControl.registerAdminLoginCallback(admin -> displayAdminPanel());
        
        displayHomePage();
    }

    @Override
    protected void show(Region region) {
        show(region, false);
    }

    private void show(Region region, boolean scrollDown) {
        ScrollPane scrollPane = new ScrollPane(region);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");
        scrollPane.setPadding(new Insets(0, 5, 0, 0));

        if (scrollDown) {
            scrollPane.setVvalue(1);
        }

        super.show(scrollPane);
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
        UserObject currentUser = loginControl.getUserObject();

        if (currentUser == null) {
            displayLoginRegister();
            return;
        }

        GridPane layout = new GridPane();

        layout.setHgap(15);
        layout.setVgap(5);

        Label header = new Label("User Panel - Welcome " + currentUser.username + "!");
        header.setFont(Font.font(30));

        Label usernameLabel = new Label("Username:");
        Label usernameText = new Label(currentUser.username);

        Label accountCreatedLabel = new Label("Account Created:");
        Label accountCreatedText = new Label(ACCOUNT_CREATED_DATE_FORMAT.format(currentUser.accountCreated));

        Label moderatorStatusLabel = new Label("Is Moderator?");
        Label moderatorStatusText = new Label(currentUser.isMod ? "Yes" : "No");

        Label muteStatusLabel = new Label("Is Muted?");
        Label muteStatusText = new Label(currentUser.isMuted ? "Yes" : "No");

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(event -> loginControl.saveUserObject(null));

        int totalCols = 5;

        layout.add(header, 0, 0, totalCols, 1);

        layout.add(usernameLabel, 0, 1);
        layout.add(usernameText, 1, 1);
        layout.add(accountCreatedLabel, 0, 2);
        layout.add(accountCreatedText, 1, 2);
        layout.add(moderatorStatusLabel, 3, 1);
        layout.add(moderatorStatusText, 4, 1);
        layout.add(muteStatusLabel, 3, 2);
        layout.add(muteStatusText, 4, 2);
        layout.add(logoutButton, 0, 3, totalCols, 1);
        layout.add(new Separator(Orientation.VERTICAL), 2, 1, 1, 2);

        if (currentUser.isMod) {
            
            int row = 4;

            ArrayList<UserObject> users = databaseManager.getAllUsers();

            Label usersHeader = new Label("Forum Users:");
            usersHeader.setFont(Font.font(15));

            layout.add(usersHeader, 0, row++, totalCols, 1);        

            for (UserObject user : users) {
                Label userLabel = new Label(user.username);
                ChangeUserMuteStatusUI changeUserMuteStatusUI = new ChangeUserMuteStatusUI(controlDirectory, currentUser);

                layout.add(userLabel, 0, row, totalCols - 2, 1);
                layout.add(changeUserMuteStatusUI.getNode(), totalCols - 2, row, 2, 1);
                row++;
            }
        }

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

        VBox mainLayout = new VBox();
        mainLayout.setSpacing(5);

        GridPane userInfoLayout = new GridPane();

        userInfoLayout.setHgap(15);
        userInfoLayout.setVgap(5);

        Label header = new Label("Admin Panel - Welcome " + admin.username + "!");
        header.setFont(Font.font(30));

        Label usernameLabel = new Label("Username");
        Label usernameText = new Label(admin.username);

        Label accountCreatedLabel = new Label("Account Created");
        Label accountCreatedText = new Label(ACCOUNT_CREATED_DATE_FORMAT.format(admin.accountCreated));

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(event -> loginControl.saveAdminObject(null));

        userInfoLayout.add(header, 0, 0, 2, 1);
        userInfoLayout.add(usernameLabel, 0, 1);
        userInfoLayout.add(usernameText, 1, 1);
        userInfoLayout.add(accountCreatedLabel, 0, 2);
        userInfoLayout.add(accountCreatedText, 1, 2);
        userInfoLayout.add(logoutButton, 0, 3, 2, 1);
        
        mainLayout.getChildren().add(userInfoLayout);

        logoutButton.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(logoutButton, Priority.ALWAYS);
        
        ArrayList<UserObject> users = databaseManager.getAllUsers();

        Label usersHeader = new Label("Forum Users:");
        usersHeader.setFont(Font.font(15));

        mainLayout.getChildren().add(usersHeader);        

        for (UserObject user : users) {
            ChangeUserMuteStatusUI changeUserMuteStatusUI = new ChangeUserMuteStatusUI(controlDirectory, user);
            ManageModeratorUI manageModeratorUI = new ManageModeratorUI(controlDirectory, user);
            DeleteAccountUI deleteAccountUI = new DeleteAccountUI(controlDirectory, this, user);

            HBox hLayout = new HBox(changeUserMuteStatusUI.getNode(), manageModeratorUI.getNode(), deleteAccountUI.getNode());
            hLayout.setSpacing(5);

            HBox.setHgrow(changeUserMuteStatusUI.getNode(), Priority.ALWAYS);
            HBox.setHgrow(manageModeratorUI.getNode(), Priority.ALWAYS);
            HBox.setHgrow(deleteAccountUI.getNode(), Priority.ALWAYS);

            mainLayout.getChildren().addAll(new Label(user.username), hLayout);
        }

        show(mainLayout);
    }

	public void displayAddPost(UserCategoryObject category) {
        AddUserPostUI addUserPostUI = new AddUserPostUI(controlDirectory, this, category);

        show(addUserPostUI.getNode());
    }
    
    public void displayPost(int postId) {
        displayPost(postId, false);
    }
    
    public void displayPost(int postId, boolean scrollDown) {
        ViewPostUI viewPostUI = new ViewPostUI(controlDirectory, this, postId);

        show(viewPostUI.getNode(), scrollDown);
    }

	public void displaySearch() {
        SearchPostsUI searchPostsUI = new SearchPostsUI(controlDirectory, this);

        show(searchPostsUI.getNode());
	}
}
