package pageturners.ui.main;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import pageturners.controls.ControlDirectory;
import pageturners.controls.LoginControl;
import pageturners.models.AdminObject;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;

public class MainWindowHeaderUI extends UIElement {

    public static final int HEIGHT = 60;

    private final MainWindowUI mainWindow;
    private final ControlDirectory controlDirectory;
    private final LoginControl loginControl;
    
    public MainWindowHeaderUI(MainWindowUI mainWindow, ControlDirectory controlDirectory) {
        this.mainWindow = mainWindow;
        this.controlDirectory = controlDirectory;
        loginControl = (LoginControl)controlDirectory.getControl(LoginControl.class);

        loginControl.registerUserLoginCallback(user -> display());
        loginControl.registerAdminLoginCallback(admin -> display());

        display();
    }

    private void display() {
        HBox layout = new HBox();
        layout.setSpacing(5);

        Button homeButton = new Button("Home Page");
        homeButton.setOnAction(event -> mainWindow.getBody().displayHomePage());

        Button forumsButton = new Button("Forums");
        forumsButton.setOnAction(event -> mainWindow.getBody().displayCategories());

        Region centerRegion = new Region();
        HBox.setHgrow(centerRegion, Priority.ALWAYS);

        UserObject user = loginControl.getUserObject();
        AdminObject admin = loginControl.getAdminObject();

        layout.getChildren().addAll(homeButton, forumsButton, centerRegion);

        if (user != null) {
            Button userPanelButton = new Button("Logged in as " + user.username);
            userPanelButton.setOnAction(event -> mainWindow.getBody().displayUserPanel());
            
            layout.getChildren().add(userPanelButton);
        }
        else if (admin != null) {
            Button adminPanelButton = new Button("Logged in as " + admin.username + " (Admin)");
            adminPanelButton.setOnAction(event -> mainWindow.getBody().displayAdminPanel());
            
            layout.getChildren().add(adminPanelButton);
        }
        else {
            Button loginRegisterButton = new Button("Login | Register");
            loginRegisterButton.setOnAction(event -> mainWindow.getBody().displayLoginRegister());

            layout.getChildren().add(loginRegisterButton);
        }


        show(layout);
    }
}
