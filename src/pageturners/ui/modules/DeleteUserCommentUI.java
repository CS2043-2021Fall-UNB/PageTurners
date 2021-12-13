package pageturners.ui.modules;

import javafx.scene.control.Button;
import pageturners.controls.ControlDirectory;
import pageturners.controls.DeleteUserCommentControl;
import pageturners.models.UserCommentObject;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;
import pageturners.ui.main.MainWindowBodyUI;

public class DeleteUserCommentUI extends UIElement {

    private final DeleteUserCommentControl deleteUserCommentControl;
    private final MainWindowBodyUI mainWindowBodyUI;
    private final UserPostObject post;
    private final UserCommentObject comment;

    public DeleteUserCommentUI(ControlDirectory controlDirectory,
        MainWindowBodyUI mainWindowBodyUI, UserPostObject post, UserCommentObject comment) {

        this.deleteUserCommentControl = (DeleteUserCommentControl)controlDirectory.getControl(DeleteUserCommentControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        this.post = post;
        this.comment = comment;

        displayDeleteCommentButton();
    }

    private void displayDeleteCommentButton() {
        Button deletePostButton = new Button("Delete Post");

        deletePostButton.setOnAction(event -> clickDeleteComment());

        show(deletePostButton);
    }

    private void clickDeleteComment() {
        UserCommentObject post = deleteUserCommentControl.handleDeleteComment(comment);

        if (post == null) {
            displayDeleteFailure();
            return;
        }
        
        displayDeleteConfirmation(post);
    }

    private void displayDeleteConfirmation(UserCommentObject userComment) {
        mainWindowBodyUI.displayPost(post.id);
    }

    private void displayDeleteFailure() {
        AlertHelper.showWarning("Comment Deletion Failed", "An error occurred when deleting this comment.");
    }
}
