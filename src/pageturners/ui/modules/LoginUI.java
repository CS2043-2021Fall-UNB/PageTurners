package pageturners.ui.modules;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import pageturners.controls.LoginControl;
import pageturners.models.AdminObject;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;

public class LoginUI extends UIElement {
    
    private final LoginControl loginControl;

    private TextField usernameField;
    private PasswordField passwordField;
    private Label outputLabel;

    public LoginUI(LoginControl loginControl) {
        this.loginControl = loginControl;

        displayLoginForm();
    }

    public void displayLoginForm() {
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);

        Label headerLabel = new Label("Already have an account? Login!");
        headerLabel.setFont(Font.font(15));

        Label userNameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        usernameField = new TextField();
        passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setMaxWidth(Double.MAX_VALUE);

        outputLabel = new Label();
        outputLabel.setWrapText(true);

        grid.add(headerLabel, 0, 0, 2, 1);
        grid.add(userNameLabel, 0, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(usernameField, 1, 1);
        grid.add(passwordField, 1, 2);
        grid.add(loginButton, 0, 3, 2, 1);
        grid.add(outputLabel, 0, 4, 2, 1);

        grid.setMaxWidth(Double.MAX_VALUE);

        GridPane.setHgrow(usernameField, Priority.ALWAYS);
        GridPane.setHgrow(passwordField, Priority.ALWAYS);

        usernameField.setOnAction(event -> clickUserLogin());
        passwordField.setOnAction(event -> clickUserLogin());
        loginButton.setOnAction(event -> clickUserLogin());

        show(grid);
    }

    private void clickUserLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        passwordField.setText("");

        if (username.length() == 0) {
            outputLabel.setText("Please enter a username.");
            return;
        }

        if (password.length() == 0) {
            outputLabel.setText("Please enter a password.");
            return;
        }
        
        AdminObject admin = loginControl.handleAdminLogin(username, password);

        if (admin != null) {
            displayAdminLoginSuccess(admin);
            return;
        }

        UserObject user = loginControl.handleUserLogin(username, password);

        if (user != null) {
            displayUserLoginSuccess(user);
            return;
        }

        displayUserLoginFailure();
    }

    private void displayUserLoginSuccess(UserObject user) {
        outputLabel.setText("Successfully logged in. Welcome " + user.username + "!");
    }

    private void displayUserLoginFailure() {
        Alert alert = new Alert(AlertType.WARNING);

        alert.setGraphic(null);
        alert.setTitle("Login Failed");
        alert.setHeaderText("Invalid Login Credentials");
        alert.setContentText("Login failed. Please check the username and password and try again.");

        alert.showAndWait();
    }

    private void displayAdminLoginSuccess(AdminObject admin) {
        outputLabel.setText("Successfully logged in as admin. Welcome " + admin.username + "!");
    }
}
