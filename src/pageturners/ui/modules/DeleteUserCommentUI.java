package pageturners.ui.modules;

import pageturners.controls.DeleteUserCommentControl;
import pageturners.models.UserCommentObject;
import pageturners.models.UserObject;

public class DeleteUserCommentUI {
    
    private final DeleteUserCommentControl deleteUserCommentControl;
    private final UserCommentObject userComment;
    private final UserObject user;

    public DeleteUserCommentUI(DeleteUserCommentControl deleteUserCommentControl, UserCommentObject userComment, UserObject user) {
        this.deleteUserCommentControl = deleteUserCommentControl;
        this.userComment = userComment;
        this.user = user;
    }
    

    public void displayDeleteCommentButton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickDeleteComment() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayDeleteConfirmation() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayDeleteFailure() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
