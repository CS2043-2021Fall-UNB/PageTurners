package pageturners.ui.modules;

import pageturners.controls.DeleteCommentControl;
import pageturners.models.UserCommentObject;
import pageturners.models.UserObject;

public class DeleteCommentUI {

    private final DeleteCommentControl deleteCommentControl;
    private final UserCommentObject userComment;
    private final UserObject user;

    public DeleteCommentUI(DeleteCommentControl deleteCommentControl, UserCommentObject userComment, UserObject user) {
        this.deleteCommentControl = deleteCommentControl;
        this.userComment = userComment;
        this.user = user;
    }

    private void displayDeleteCommentButton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickDeleteComment() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayDeleteConfirmation(UserCommentObject userComment) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayDeleteFailure(UserCommentObject userComment) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
