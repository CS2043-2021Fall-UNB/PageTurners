package pageturners.ui.modules;

import javafx.scene.control.Button;
import pageturners.controls.ControlDirectory;
import pageturners.controls.DeletePostControl;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;
import pageturners.ui.main.MainWindowBodyUI;

public class DeletePostUI extends UIElement {
    
    private final DeletePostControl deletePostControl;
    private final MainWindowBodyUI mainWindowBodyUI;
    private final UserPostObject userPost;

    public DeletePostUI(ControlDirectory controlDirectory, MainWindowBodyUI mainWindowBodyUI, UserPostObject userPost) {
        deletePostControl = (DeletePostControl)controlDirectory.getControl(DeletePostControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        this.userPost = userPost;

        displayDeletePostButton();
    }

    public void displayDeletePostButton() {
        Button deletePostButton = new Button("Delete Post");

        deletePostButton.setOnAction(event -> clickDeletePost());

        show(deletePostButton);
    }

    private void clickDeletePost() {
        UserPostObject post = deletePostControl.handleDeletePost(userPost.id);

        if (post == null) {
            displayFailure();
            return;
        }
        
        displayConfirmation(post);
    }

    private void displayConfirmation(UserPostObject post) {
        mainWindowBodyUI.displayPost(post.id);
    }

    private void displayFailure() {
        AlertHelper.showWarning("Post Deletion Failed", "An error occurred when deleting this post.");
    }
}
