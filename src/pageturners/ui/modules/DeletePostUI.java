package pageturners.ui.modules;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;
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

    private Region shownRegion;

    public DeletePostUI(ControlDirectory controlDirectory, MainWindowBodyUI mainWindowBodyUI, UserPostObject userPost) {
        deletePostControl = (DeletePostControl)controlDirectory.getControl(DeletePostControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        this.userPost = userPost;

        displayDeletePostButton();
    }
    
    @Override
    public Region getNode() {
        if (shownRegion != null) {
            return shownRegion;
        }

        return super.getNode();
    }

    public void displayDeletePostButton() {
        Button deletePostButton = new Button("Delete Post");

        deletePostButton.setOnAction(event -> clickDeletePost());

        shownRegion = deletePostButton;
        //show(deletePostButton);
    }

    private void clickDeletePost() {
        if (!AlertHelper.showYesNo("Deleting Post", "Are you sure you'd like to delete this post?")) {
            return;
        }

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
