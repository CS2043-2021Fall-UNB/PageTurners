package pageturners.ui.modules;

import javafx.scene.control.Button;
import pageturners.controls.ControlDirectory;
import pageturners.controls.DeleteUserAccountControl;
import pageturners.controls.LoginControl;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;

public class DeleteUserAccountUI extends UIElement {

    private final DeleteUserAccountControl deleteUserAccountControl;
    private final LoginControl loginControl;
    private final UserObject user;

    public DeleteUserAccountUI(ControlDirectory controlDirectory, UserObject user) {
        this.deleteUserAccountControl = (DeleteUserAccountControl)controlDirectory.getControl(DeleteUserAccountControl.class);
        this.loginControl = (LoginControl)controlDirectory.getControl(LoginControl.class);
        this.user = user;

        displayDeleteAccountButton();
    }

    public void displayDeleteAccountButton() {
        Button deleteAccountButton = new Button("Delete Account");
        deleteAccountButton.setOnAction(event -> clickDeleteAccount());

        show(deleteAccountButton);
    }

    private void clickDeleteAccount() {
        if (!AlertHelper.showYesNo("Deleting Account", "Are you sure you'd like to delete your account?")) {
            return;
        }

        if(!deleteUserAccountControl.handleDeleteAccount(user.id)){
            displayFailure();
            return;
        }

        displayDeleteConfirmation(user);
    }

    private void displayDeleteConfirmation(UserObject user) {
        AlertHelper.showWarning("Account Deletion Successful", "You have successfully deleted your account " + user.username + ".");
    }

    private void displayFailure() {
        AlertHelper.showWarning("Account Deletion Failed", "An error occurred when deleting your account.");
    }
}
