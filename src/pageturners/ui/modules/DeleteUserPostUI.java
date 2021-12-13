package pageturners.ui.modules;

import javafx.scene.control.Button;
import pageturners.controls.ControlDirectory;
import pageturners.controls.DeleteUserPostControl;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;
import pageturners.ui.main.MainWindowBodyUI;

public class DeleteUserPostUI extends UIElement {
    
    private final DeleteUserPostControl deleteUserPostControl;
    private final MainWindowBodyUI mainWindowBodyUI;
    private final UserPostObject userPost;

    public DeleteUserPostUI(ControlDirectory controlDirectory, MainWindowBodyUI mainWindowBodyUI, UserPostObject userPost) {
        deleteUserPostControl = (DeleteUserPostControl)controlDirectory.getControl(DeleteUserPostControl.class);
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
        UserPostObject post = deleteUserPostControl.handleDeletePost(userPost);

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
