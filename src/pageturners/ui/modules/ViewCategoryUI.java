package pageturners.ui.modules;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pageturners.controls.ViewCategoryControl;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;

public class ViewCategoryUI extends UIElement {

    private final ViewCategoryControl viewCategoryControl;

    public ViewCategoryUI(ViewCategoryControl viewCategoryControl) {
        this.viewCategoryControl = viewCategoryControl;

        displayCategories();
    }

    public void displayCategories() {
        ArrayList<UserCategoryObject> categories = viewCategoryControl.handleViewCategories();

        displayCategoryObjects(categories);
    }

    private void displayCategoryObjects(ArrayList<UserCategoryObject> categories) {
        VBox layout = new VBox();
        layout.setSpacing(5);
        //layout.setStyle("-fx-background-color: #0000FF;");

        Label label = new Label("Categories:");
        label.setFont(Font.font(20));
        label.prefWidthProperty().bind(layout.prefWidthProperty());

        layout.getChildren().clear();
        layout.getChildren().add(label);

        for (UserCategoryObject category : categories) {
            Button button = new Button(category.categoryName);
            button.setOnAction((event) -> onClickCategory(event, category));
            button.prefWidthProperty().bind(layout.widthProperty().subtract(20));

            layout.getChildren().add(button);
        }
        
        show(layout);
    }

    private void onClickCategory(ActionEvent event, UserCategoryObject category) {
        //label.setText("Categories: (Clicked " + category.categoryName + ")");
    }

    private void clickSelectCategory() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayPosts(UserPostObject[] posts) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayNoPosts() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
