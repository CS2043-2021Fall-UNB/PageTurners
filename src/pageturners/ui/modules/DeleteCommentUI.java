package pageturners.ui.modules;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import pageturners.controls.ControlDirectory;
import pageturners.controls.DeleteCommentControl;
import pageturners.models.UserCommentObject;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;
import pageturners.ui.main.MainWindowBodyUI;

public class DeleteCommentUI extends UIElement {

    private final DeleteCommentControl deleteCommentControl;
    private final MainWindowBodyUI mainWindowBodyUI;
    private final UserPostObject post;
    private final UserCommentObject comment;

    private Region shownRegion;

    public DeleteCommentUI(ControlDirectory controlDirectory,
        MainWindowBodyUI mainWindowBodyUI, UserPostObject post, UserCommentObject comment) {

        this.deleteCommentControl = (DeleteCommentControl)controlDirectory.getControl(DeleteCommentControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        this.post = post;
        this.comment = comment;

        displayDeleteCommentButton();
    }
    
    @Override
    public Region getNode() {
        if (shownRegion != null) {
            return shownRegion;
        }

        return super.getNode();
    }

    private void displayDeleteCommentButton() {
        Button deleteCommentButton = new Button("Delete Comment");

        deleteCommentButton.setOnAction(event -> clickDeleteComment());

        shownRegion = deleteCommentButton;
        //show(deletePostButton);
    }

    private void clickDeleteComment() {
        if (!AlertHelper.showYesNo("Deleting Comment", "Are you sure you'd like to delete this comment?")) {
            return;
        }
        
        UserCommentObject post = deleteCommentControl.handleDeleteComment(comment.id);

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
