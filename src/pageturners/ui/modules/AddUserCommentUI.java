package pageturners.ui.modules;

import pageturners.controls.AddUserCommentControl;
import pageturners.models.UserCommentObject;
import pageturners.models.UserPostObject;

public class AddUserCommentUI {
    private final AddUserCommentControl addUserCommentControl;
    private final UserPostObject userPost;

    public AddUserCommentUI(AddUserCommentControl addUserCommentControl, UserPostObject userPost) {
        this.addUserCommentControl = addUserCommentControl;
        this.userPost = userPost;
    }

    public void displayAddCommentForm() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickAddComment() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayAddCommentConfirmation(UserCommentObject comment) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
