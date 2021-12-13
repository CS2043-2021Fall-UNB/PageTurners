package pageturners.ui.modules;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import pageturners.controls.AddUserCommentControl;
import pageturners.controls.ControlDirectory;
import pageturners.models.UserCommentObject;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;
import pageturners.ui.main.MainWindowBodyUI;

public class AddUserCommentUI extends UIElement {
    private final AddUserCommentControl addUserCommentControl;
    private final MainWindowBodyUI mainWindowBodyUI;
    private final UserPostObject post;

    private TextArea contentsText;

    public AddUserCommentUI(ControlDirectory controlDirectory, MainWindowBodyUI mainWindowBodyUI, UserPostObject post) {
        this.addUserCommentControl = (AddUserCommentControl)controlDirectory.getControl(AddUserCommentControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        this.post = post;

        displayAddCommentForm();
    }

    public void displayAddCommentForm() {

        VBox layout = new VBox();

        contentsText = new TextArea();
        contentsText.setTextFormatter(new TextFormatter<String>(
            change -> change.getControlNewText().length() <= 512 ? change : null));

        Region spacer = new Region();
                
        Button addCommentButton = new Button("Add Comment");
        addCommentButton.setOnAction(event -> clickAddComment());

        layout.getChildren().addAll(contentsText, new HBox(spacer, addCommentButton));

        HBox.setHgrow(spacer, Priority.ALWAYS);

        show(layout);
    }

    private void clickAddComment() {
        String contents = contentsText.getText();

        if (contents == null || contents.length() == 0) {
            displayAddCommentFailure("Please give this post some contents.");
            return;
        }
        
        if (contents.length() > 512) {
            displayAddCommentFailure("Your post must be less than or equal to 1024 characters.");
            return;
        }

        UserCommentObject comment = addUserCommentControl.handleAddComment(post.id, contents);

        if (comment == null) {
            displayAddCommentFailure("An error occurred while attempting to add this comment. Please try again later.");
            return;
        }

        displayAddCommentConfirmation(comment);
    }

    private void displayAddCommentConfirmation(UserCommentObject comment) {
        mainWindowBodyUI.displayPost(comment.postId);
    }

    private void displayAddCommentFailure(String message) {
        AlertHelper.showWarning("Add Comment Failed", message);
    }
}
