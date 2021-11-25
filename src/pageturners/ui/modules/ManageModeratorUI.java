package pageturners.ui.modules;

import pageturners.controls.ManageModeratorControl;
import pageturners.models.UserObject;

public class ManageModeratorUI {
    private final ManageModeratorControl manageModeratorControl;
    private UserObject[] users;

    public ManageModeratorUI(ManageModeratorControl manageModeratorControl) {
        this.manageModeratorControl = manageModeratorControl;
    }

    public void displayAllUsers() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void grantModeratorPrivilege() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void revokeModeratorPrivilege() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayAllUserObjects(UserObject[] users) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayUpdateConfirmation(UserObject user) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayUpdateFailure() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
