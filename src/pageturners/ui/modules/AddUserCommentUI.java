package pageturners.ui.modules;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
        HBox layout = new HBox();
        layout.setSpacing(5);

        contentsText = new TextArea();
        contentsText.setTextFormatter(new TextFormatter<String>(
            change -> change.getControlNewText().length() <= 512 ? change : null));
        contentsText.setPrefWidth(200);
                
        Button addCommentButton = new Button("Add Comment");
        addCommentButton.setPrefWidth(100);
        addCommentButton.setOnAction(event -> clickAddComment());

        contentsText.prefHeightProperty().bind(layout.heightProperty());
        addCommentButton.prefHeightProperty().bind(layout.heightProperty());

        layout.getChildren().addAll(contentsText, addCommentButton);

        HBox.setHgrow(contentsText, Priority.ALWAYS);
        HBox.setHgrow(addCommentButton, Priority.NEVER);

        show(layout);
    }

    private void clickAddComment() {
        String contents = contentsText.getText();

        if (contents == null || contents.length() == 0) {
            displayAddCommentFailure("Please give this comment some contents.");
            return;
        }
        
        if (contents.length() > 512) {
            displayAddCommentFailure("Your comment must be less than or equal to 512 characters.");
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
        mainWindowBodyUI.displayPost(comment.postId, true);
    }

    private void displayAddCommentFailure(String message) {
        AlertHelper.showWarning("Add Comment Failed", message);
    }
}
