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
import pageturners.controls.CreateAccountControl;
import pageturners.models.UserCreationInfo;
import pageturners.models.UserCreationResult;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;

public class CreateAccountUI extends UIElement {
    
    private final CreateAccountControl createAccountControl;

    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button registerButton;
    private Label outputLabel;

    public CreateAccountUI(CreateAccountControl createAccountControl) {
        this.createAccountControl = createAccountControl;

        displayAccountCreationForm();
    }

    public void displayAccountCreationForm() {
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);

        Label headerLabel = new Label("Don't have an account? Create one!");
        headerLabel.setFont(Font.font(15));

        Label userNameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label confirmPasswordLabel = new Label("Confirm Password:");

        usernameField = new TextField();
        passwordField = new PasswordField();
        confirmPasswordField = new PasswordField();

        registerButton = new Button("Register");
        registerButton.setMaxWidth(Double.MAX_VALUE);

        outputLabel = new Label();
        outputLabel.setWrapText(true);

        usernameField.setOnAction(event -> clickCreateAccount());
        passwordField.setOnAction(event -> clickCreateAccount());
        confirmPasswordField.setOnAction(event -> clickCreateAccount());
        registerButton.setOnAction(event -> clickCreateAccount());

        grid.add(headerLabel, 0, 0, 2, 1);
        grid.add(userNameLabel, 0, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(confirmPasswordLabel, 0, 3);
        grid.add(usernameField, 1, 1);
        grid.add(passwordField, 1, 2);
        grid.add(confirmPasswordField, 1, 3);
        grid.add(registerButton, 0, 4, 2, 1);
        grid.add(outputLabel, 0, 5, 2, 1);

        GridPane.setHgrow(usernameField, Priority.ALWAYS);
        GridPane.setHgrow(passwordField, Priority.ALWAYS);
        GridPane.setHgrow(confirmPasswordField, Priority.ALWAYS);

        grid.setMaxWidth(Double.MAX_VALUE);

        show(grid);
    }

    private void clickCreateAccount() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            displayErrors("The passwords entered do not match.");
            return;
        }

        UserCreationInfo creationInfo = new UserCreationInfo();

        creationInfo.username = username;
        creationInfo.password = password;

        UserCreationResult result = createAccountControl.handleCreateAccount(creationInfo);

        if (result.passwordResult != null || result.usernameResult != null) {
            String errors;

            if (result.passwordResult == null) {
                errors = result.usernameResult;
            }
            else if (result.userResult == null) {
                errors = result.passwordResult;
            }
            else {
                errors = result.usernameResult + "\n" + result.passwordResult;
            }

            displayErrors(errors);
            return;
        }

        if (result.userResult == null) {
            throw new RuntimeException("Invalid operation - no account creation error result or success result");
        }

        displayConfirmation(result.userResult);
    }

    private void displayConfirmation(UserObject user) {
        outputLabel.setText("Account " + user.username + " successfully created. Please login using this account.");

        usernameField.setDisable(true);
        passwordField.setDisable(true);
        confirmPasswordField.setDisable(true);
        registerButton.setDisable(true);
    }

    private void displayErrors(String message) {
        Alert alert = new Alert(AlertType.WARNING);

        alert.setGraphic(null);
        alert.setTitle("Registration Failed");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
