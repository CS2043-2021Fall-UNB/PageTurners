package pageturners.ui.modules;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import pageturners.controls.LoginControl;
import pageturners.models.AdminObject;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;

public class LoginUI extends UIElement {
    
    private final LoginControl loginControl;

    public LoginUI(LoginControl loginControl) {
        this.loginControl = loginControl;

        displayLoginForm();
    }

    public void displayLoginForm() {
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);

        Label userNameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        TextField userNameField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setMaxWidth(Double.MAX_VALUE);

        grid.add(userNameLabel, 0, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(userNameField, 1, 0);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 0, 2, 2, 1);

        GridPane.setFillWidth(loginButton, true);
        grid.maxWidth(Double.MAX_VALUE);

        show(grid);
    }

    private void clickUserLogin() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayUserLoginSuccess(UserObject user) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayUserLoginFailure() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickAdminLogin() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayAdminLoginSuccess(AdminObject admin) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayAdminLoginFailure() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
