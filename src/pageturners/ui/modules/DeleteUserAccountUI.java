package pageturners.ui.modules;

import pageturners.controls.ControlDirectory;
import pageturners.controls.DeleteUserAccountControl;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;

public class DeleteUserAccountUI extends UIElement {

    private final DeleteUserAccountControl deleteUserAccountControl;
    private final UserObject userObject;

    public DeleteUserAccountUI(ControlDirectory controlDirectory, UserObject userObject) {
        this.deleteUserAccountControl = (DeleteUserAccountControl)controlDirectory.getControl(DeleteUserAccountControl.class);
        this.userObject = userObject;
    }

    public void displayDeleteAccountForm() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickDeleteAccount() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayDeleteConfirmation() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickConfirmDelete() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickCancelDelete() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayConfirmation() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayFailure() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
