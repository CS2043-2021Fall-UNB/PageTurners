package pageturners.ui.modules;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import pageturners.controls.AddUserPostControl;
import pageturners.controls.ControlDirectory;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;
import pageturners.ui.main.MainWindowBodyUI;

public class AddUserPostUI extends UIElement {

    private final AddUserPostControl addUserPostControl;
    private MainWindowBodyUI mainWindowBodyUI;
    private final UserCategoryObject category;

    private TextField titleText;
    private TextArea contentsText;

    public AddUserPostUI(ControlDirectory controlDirectory, MainWindowBodyUI mainWindowBodyUI, UserCategoryObject category) {
        this.addUserPostControl = (AddUserPostControl)controlDirectory.getControl(AddUserPostControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        this.category = category;

        displayAddPostForm();
    }

    public void displayAddPostForm() {

        GridPane layout = new GridPane();
        layout.setHgap(5);
        layout.setVgap(5);
        layout.setMaxWidth(Double.MAX_VALUE);

        Label headerLabel = new Label("Create Post in " + category.categoryName);
        headerLabel.setFont(Font.font(15));

        Label titleLabel = new Label("Title:");
        titleText = new TextField();

        Label contentsLabel = new Label("Contents:");
        contentsText = new TextArea();
        contentsText.setTextFormatter(new TextFormatter<String>(
            change -> change.getControlNewText().length() <= 1024 ? change : null));
        
        Button createPostButton = new Button("Create Post");
        createPostButton.setMaxWidth(Double.MAX_VALUE);
        createPostButton.setOnAction(event -> clickAddPost());

        layout.add(headerLabel, 0, 0, 2, 1);
        layout.add(titleLabel, 0, 1);
        layout.add(titleText, 1, 1);
        layout.add(contentsLabel, 0, 2);
        layout.add(contentsText, 0, 3, 2, 1);
        layout.add(createPostButton, 0, 4, 2, 1);

        GridPane.setHgrow(titleText, Priority.ALWAYS);
        GridPane.setHgrow(contentsText, Priority.ALWAYS);
        GridPane.setVgrow(contentsText, Priority.ALWAYS);
        GridPane.setHgrow(createPostButton, Priority.ALWAYS);

        show(layout);
    }

    private void clickAddPost() {
        String title = titleText.getText();
        String contents = contentsText.getText();

        if (title == null || title.length() == 0) {
            displayAddPostFailure("Please give this post a title.");
            return;
        }

        if (contents == null || contents.length() == 0) {
            displayAddPostFailure("Please give this post some contents.");
            return;
        }
        
        if (contents.length() > 1024) {
            displayAddPostFailure("Your post must be less than or equal to 1024 characters.");
            return;
        }

        UserPostObject post = addUserPostControl.handleAddPost(category.categoryId, title, contents);

        if (post == null) {
            displayAddPostFailure("An error occurred while attempting to add this post. Please try again later.");
            return;
        }
        displayAddPostConfirmation(post);
    }

    private void displayAddPostConfirmation(UserPostObject post) {
        mainWindowBodyUI.displayPost(post.id);
    }

    private void displayAddPostFailure(String message) {
        AlertHelper.showWarning("Create Post Failed", message);
    }
}
