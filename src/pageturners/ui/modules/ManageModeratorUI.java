package pageturners.ui.modules;

import javafx.scene.control.Button;
import pageturners.controls.ControlDirectory;
import pageturners.controls.ManageModeratorControl;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;

public class ManageModeratorUI extends UIElement {
    private final ManageModeratorControl manageModeratorControl;
    private UserObject user;

    public ManageModeratorUI(ControlDirectory controlDirectory, UserObject user) {
        this.manageModeratorControl = (ManageModeratorControl)controlDirectory.getControl(ManageModeratorControl.class);
        this.user = user;

        displayManageModeratorButton();
    }

    public void displayManageModeratorButton() {
        Button button;

        if (user.isMod) {
            button = new Button("Revoke Moderator Privileges");
            button.setOnAction(event -> revokeModeratorPrivilege());
        }
        else {
            button = new Button("Grant Moderator Privileges");
            button.setOnAction(event -> grantModeratorPrivilege());
        }

        show(button);
    }

    private void grantModeratorPrivilege() {
        if (!AlertHelper.showYesNo("Grant Moderator Privileges", "Are you sure you'd like to grant moderator privileges to " + user.username + "?")) {
            return;
        }

        if (manageModeratorControl.handleUpdateModeratorPrivilege(user.id, true)) {
            displayGrantConfirmation();
        }
        else {
            displayGrantFailure();
        }
    }

    private void revokeModeratorPrivilege() {
        if (!AlertHelper.showYesNo("Revoke Moderator Privileges", "Are you sure you'd like to revoke moderator privileges from " + user.username + "?")) {
            return;
        }

        if (manageModeratorControl.handleUpdateModeratorPrivilege(user.id, false)) {
            displayRevokeConfirmation();
        }
        else {
            displayRevokeFailure();
        }
    }

    private void displayGrantConfirmation() {
        user.isMod = true;
        displayManageModeratorButton();

        AlertHelper.showInfo("Grant Moderator Privileges Successful", user.username + "'s has been granted moderator privileges.");
    }

    private void displayGrantFailure() {
        AlertHelper.showWarning("Grant Moderator Privileges Failed", "An error occurred while attempting to grant moderator privileges to " + user.username + ".");
    }

    private void displayRevokeConfirmation() {
        user.isMod = false;
        displayManageModeratorButton();

        AlertHelper.showInfo("Revoke Moderator Privileges Successful", user.username + "'s moderator privileges have been revoked.");
    }

    private void displayRevokeFailure() {
        AlertHelper.showWarning("Revoke Moderator Privileges Failed", "An error occurred while attempting to revoke moderator privileges from " + user.username + ".");
    }
}
