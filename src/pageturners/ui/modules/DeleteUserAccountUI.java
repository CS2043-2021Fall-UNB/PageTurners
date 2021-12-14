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

        displayDeleteAccountButton();
    }

    public Region getNode() {
        if (shownRegion != null) {
            return shownRegion;
        }

        return super.getNode();
    }

    public void displayDeleteAccountButton() {
        Button deleteAccountButton = new Button("Delete Account");
        deleteAccountButton.setOnAction(event -> clickDeleteAccount());

        shownRegion = deleteAccountButton;
    }

    private void clickDeleteAccount() {
        if (!AlertHelper.showYesNo("Deleting Account", "Are you sure you'd like to delete this Account?")) {
            return;
        }

        UserObject user = deleteUserAccountControl.handleDeleteAccount(user.id);

        if(user = null){
            displayFailure();
            return;
        }

        displayDeleteConfirmation(user);
    }

    private void displayDeleteConfirmation(UserObject user) {
        //mainWindowBodyUI.displayPost(user.id);
    }

//    private void clickConfirmDelete() {
//        throw new UnsupportedOperationException("Not implemented");
//    }
//
//    private void clickCancelDelete() {
//        throw new UnsupportedOperationException("Not implemented");
//    }
//
//    private void displayConfirmation() {
//        throw new UnsupportedOperationException("Not implemented");
//    }

    private void displayFailure() {
        AlertHelper.showWarning("Account Deletion Failed", "An error occurred when deleting this Account.");
    }
}
