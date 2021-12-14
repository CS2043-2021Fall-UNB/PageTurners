package pageturners.ui.modules;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pageturners.controls.ControlDirectory;
import pageturners.controls.LoginControl;
import pageturners.controls.ViewCategoryControl;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;
import pageturners.ui.main.MainWindowBodyUI;

public class ViewCategoryUI extends UIElement {

    private final ViewCategoryControl viewCategoryControl;
    private final MainWindowBodyUI mainWindowBodyUI;
    private final LoginControl loginControl;

    public ViewCategoryUI(ControlDirectory controlDirectory,
        MainWindowBodyUI mainWindowBodyUI) {

        this.viewCategoryControl = (ViewCategoryControl)controlDirectory.getControl(ViewCategoryControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        this.loginControl = (LoginControl)controlDirectory.getControl(LoginControl.class);

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

        layout.getChildren().add(label);

        for (UserCategoryObject category : categories) {
            Button button = new Button(category.categoryName);
            button.setOnAction(event -> clickSelectCategory(category));
            button.setStyle("-fx-alignment: BASELINE_LEFT;");
            button.prefWidthProperty().bind(layout.widthProperty());

            layout.getChildren().add(button);
        }
        
        show(layout);
    }

    private void clickSelectCategory(UserCategoryObject category) {
        ArrayList<UserPostObject> posts = viewCategoryControl.handleViewCategory(category.categoryId);

        displayPosts(category, posts);
    }

    private void displayPosts(UserCategoryObject category, ArrayList<UserPostObject> posts) {
        VBox layout = new VBox();
        layout.setSpacing(5);

        HBox header = new HBox();

        Label headerLabel = new Label(category.categoryName + " Posts:");
        headerLabel.setFont(Font.font(20));
        header.getChildren().add(headerLabel);

        Region region = new Region();
        header.getChildren().add(region);
        HBox.setHgrow(region, Priority.ALWAYS);

        if (loginControl.getUserObject() == null) {
            Button loginButton = new Button("Login to Create Posts");
            loginButton.setOnAction(event -> mainWindowBodyUI.displayLoginRegister());
            header.getChildren().add(loginButton);
        }
        else if (!loginControl.getUserObject().isMuted) {
            Button createPostButton = new Button("Create Post");
            createPostButton.setOnAction(event -> mainWindowBodyUI.displayAddPost(category));
            header.getChildren().add(createPostButton);
        }

        layout.getChildren().add(header);

        if (posts.size() == 0) {
            Label noPostsLabel = new Label("Looks like there aren't any posts here. Why don't you be the first?");
            layout.getChildren().add(noPostsLabel);
        }
        else {
            for (UserPostObject post : posts) {
                Button postButton = new Button(post.title + " by " + post.author.username);
                postButton.prefWidthProperty().bind(layout.widthProperty());
                postButton.setStyle("-fx-alignment: BASELINE_LEFT;");
                postButton.setOnAction(event -> clickSelectPost(post));
    
                layout.getChildren().add(postButton);
            }
        }
        
        show(layout);
    }

    private void clickSelectPost(UserPostObject post) {
        mainWindowBodyUI.displayPost(post.id);
    }
}
