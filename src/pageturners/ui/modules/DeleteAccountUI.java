package pageturners.ui.modules;

import javafx.scene.control.Button;
import pageturners.controls.ControlDirectory;
import pageturners.controls.DeleteAccountControl;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;
import pageturners.ui.main.MainWindowBodyUI;

public class DeleteAccountUI extends UIElement {

    private final DeleteAccountControl deleteAccountControl;
    private final MainWindowBodyUI mainWindowBodyUI;
    private final UserObject user;

    public DeleteAccountUI(ControlDirectory controlDirectory, MainWindowBodyUI mainWindowBodyUI, UserObject user) {
        this.deleteAccountControl = (DeleteAccountControl)controlDirectory.getControl(DeleteAccountControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        this.user = user;

        displayDeleteButton();
    }

    public void displayDeleteButton() {
        Button deleteAccountButton = new Button("Delete Account");

        deleteAccountButton.setOnAction(event -> displayConfirmationRequest());

        show(deleteAccountButton);
    }

    private void displayConfirmationRequest() {
        if (AlertHelper.showYesNo("Delete Account", "Are you sure you'd like to delete " + user.username + "'s account?")) {
            clickConfirmDeletion();
        }
    }

    private void clickConfirmDeletion() {
        if (deleteAccountControl.handleDeleteAccount(user.id)) {
            displayConfirmation();
        }
        else {
            displayFailure();
        }
    }

    private void displayConfirmation() {
        mainWindowBodyUI.displayAdminPanel();

        AlertHelper.showInfo("Account Deleted", user.username + "'s account has been deleted.");
    }

    private void displayFailure() {
        AlertHelper.showWarning("Account Deletion Failed", "An error occurred while attempting to delete " + user.username + "'s account.");
    }
}
