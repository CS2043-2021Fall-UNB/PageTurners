package pageturners.ui.modules;

import pageturners.controls.DeleteUserPostControl;
import pageturners.models.UserPostObject;

public class DeleteUserPostUI {
    
    private final DeleteUserPostControl deleteUserPostControl;
    private final UserPostObject userPost;

    public DeleteUserPostUI(DeleteUserPostControl deleteUserPostControl, UserPostObject userPost) {
        this.deleteUserPostControl = deleteUserPostControl;
        this.userPost = userPost;
    }

    public void displayDeletePostButton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickDeletePost() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayConfirmation() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayFailure() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
