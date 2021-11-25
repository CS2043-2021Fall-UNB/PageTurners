package pageturners.ui.modules;

import pageturners.controls.DeletePostControl;
import pageturners.models.UserPostObject;

public class DeletePostUI {
    
    private final DeletePostControl deletePostControl;
    private final UserPostObject userPost;

    public DeletePostUI(DeletePostControl deletePostControl, UserPostObject userPost) {
        this.deletePostControl = deletePostControl;
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
