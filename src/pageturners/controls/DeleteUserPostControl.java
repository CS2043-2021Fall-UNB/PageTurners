package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserObject;
import pageturners.models.UserPostObject;

public class DeleteUserPostControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public DeleteUserPostControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserPostObject handleDeletePost(UserPostObject post) {
        UserObject user = loginControl.getUserObject();

        if (user == null) {
            return null;
        }

        if (user.id != post.authorId) {
            return null;
        }

        return databaseManager.deletePost(post.id);
    }
}
