package pageturners.ui.modules;

import java.text.DateFormat;

import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import jdk.management.resource.internal.TotalResourceContext;
import pageturners.controls.ControlDirectory;
import pageturners.controls.LoginControl;
import pageturners.controls.ViewPostControl;
import pageturners.models.UserCommentObject;
import pageturners.models.UserPostContentObject;
import pageturners.ui.UIElement;
import pageturners.ui.main.MainWindowBodyUI;

public class ViewPostUI extends UIElement {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateTimeInstance();
    
    private final ControlDirectory controlDirectory;
    private final ViewPostControl viewPostControl;
    private final LoginControl loginControl;
    private final MainWindowBodyUI mainWindowBodyUI;
    private final int postId;

    public ViewPostUI(ControlDirectory controlDirectory, MainWindowBodyUI mainWindowBodyUI, int postId) {
        this.controlDirectory = controlDirectory;
        viewPostControl = (ViewPostControl)controlDirectory.getControl(ViewPostControl.class);
        loginControl = (LoginControl)controlDirectory.getControl(LoginControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        this.postId = postId;

        displayPost();
    }

    public void displayPost() {
        UserPostContentObject postContent = viewPostControl.handleViewPost(postId);

        if (postContent == null) {
            displayFailure();
            return;
        }

        displayPostContent(postContent);
    }

    private void displayPostContent(UserPostContentObject postContent) {
        GridPane layout = new GridPane();
        layout.setVgap(5);
        layout.setHgap(15);
        layout.setMaxWidth(Double.MAX_VALUE);

        Label title = new Label(postContent.userPost.title);
        title.setFont(Font.font(20));

        Label author = new Label("By " + postContent.author.username);
        Label postDate = new Label("Posted on " + DATE_FORMAT.format(postContent.userPost.postDate));
        Region spacer = new Region();
        Region deleteButton = null;

        if (!postContent.userPost.isDeleted) {
            if (loginControl.getAdminObject() != null) {
                DeletePostUI deletePostUI = new DeletePostUI(controlDirectory, mainWindowBodyUI, postContent.userPost);
                deleteButton = deletePostUI.getNode();
            }
            else if (loginControl.getUserObject() != null && loginControl.getUserObject().id == postContent.author.id) {
                DeleteUserPostUI deleteUserPostUI = new DeleteUserPostUI(controlDirectory, mainWindowBodyUI, postContent.userPost);
                deleteButton = deleteUserPostUI.getNode();
            }
        }

        if (deleteButton != null) {
            deleteButton.minWidthProperty().bind(deleteButton.prefWidthProperty().add(100));
        }

        Label contents = new Label(postContent.userPost.isDeleted ? "This post was deleted." : postContent.userPost.contents);
        contents.setWrapText(true);
        contents.setPrefWidth(Double.MAX_VALUE);

        Label commentsHeader = new Label("Comments:");
        commentsHeader.setFont(Font.font(15));

        int totalCols = 4;

        layout.add(title, 0, 0, totalCols, 1);
        layout.add(new Separator(Orientation.HORIZONTAL), 0, 1, totalCols, 1);
        layout.add(author, 0, 2);
        layout.add(postDate, 1, 2);
        layout.add(spacer, 2, 2);
        if (deleteButton != null) {
            layout.add(deleteButton, 3, 2);
        }
        layout.add(new Separator(Orientation.HORIZONTAL), 0, 3, totalCols, 1);
        layout.add(contents, 0, 4, totalCols, 1);
        layout.add(new Separator(Orientation.HORIZONTAL), 0, 5, totalCols, 1);
        layout.add(commentsHeader, 0, 6, totalCols, 1);

        if (deleteButton != null) {
            GridPane.setHgrow(spacer, Priority.ALWAYS);
        }

        int commentRow = 7;

        if (postContent.userComments.size() == 0) {
            Label noCommentsLabel = new Label("There are no comments at the moment. Be the first!");
            layout.add(noCommentsLabel, 0, commentRow++, totalCols, 1);
        }
        else {
            for (UserCommentObject comment : postContent.userComments) {
                Region commentUI = getCommentUI(postContent, comment);
                layout.add(commentUI, 0, commentRow++, 3, 1);
            }
        }

        if (loginControl.getUserObject() == null) {
            Button loginButton = new Button("Login to comment");
            loginButton.setOnAction(event -> mainWindowBodyUI.displayLoginRegister());
            layout.add(loginButton, 0, commentRow++, totalCols, 1);
        }
        else {
            AddUserCommentUI addUserCommentUI = new AddUserCommentUI(controlDirectory, mainWindowBodyUI, postContent.userPost);
            layout.add(addUserCommentUI.getNode(), 0, commentRow++, totalCols, 1);
        }

        show(layout);
    }

    private Region getCommentUI(UserPostContentObject postContent, UserCommentObject comment) {
        GridPane layout = new GridPane();

        Label author = new Label(comment.user.username + ":");
        Label commentDate = new Label(DATE_FORMAT.format(comment.commentDate));

        int totalCols = 0;

        layout.add(author, totalCols++, 0);
        layout.add(commentDate, totalCols++, 0);

        if (loginControl.getAdminObject() != null) {
            DeleteCommentUI deleteCommentUI = new DeleteCommentUI(controlDirectory, mainWindowBodyUI, postContent.userPost, comment);

            layout.add(deleteCommentUI.getNode(), totalCols++, 0);
        }
        else if (loginControl.getUserObject() != null && loginControl.getUserObject().id == postContent.author.id) {
            DeleteUserCommentUI deleteUserCommentUI = new DeleteUserCommentUI(controlDirectory, mainWindowBodyUI, postContent.userPost, comment);

            layout.add(deleteUserCommentUI.getNode(), totalCols++, 0);
        }

        Label commentContents = new Label(comment.contents);
        commentContents.setWrapText(true);

        layout.add(commentContents, 0, 1, totalCols, 1);

        layout.setHgap(10);
        layout.setVgap(5);

        return layout;
    }

    private void displayFailure() {
        Label header = new Label("Uh oh! Looks like we can't find that post. Please try again later.");
        header.setFont(Font.font(15));

        show(header);
    }
}
