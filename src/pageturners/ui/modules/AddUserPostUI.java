package pageturners.ui.modules;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import pageturners.controls.AddUserPostControl;
import pageturners.controls.ControlDirectory;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;

public class AddUserPostUI extends UIElement {

    private final AddUserPostControl addUserPostControl;
    private final UserCategoryObject category;

    public AddUserPostUI(ControlDirectory controlDirectory, UserCategoryObject category) {
        this.addUserPostControl = (AddUserPostControl)controlDirectory.getControl(AddUserPostControl.class);
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
        TextField titleText = new TextField();

        Label contentsLabel = new Label("Contents:");
        TextArea contentsText = new TextArea();
        
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
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayAddPostConfirmation(UserPostObject post) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
