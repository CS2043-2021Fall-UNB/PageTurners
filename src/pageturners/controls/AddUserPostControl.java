package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserObject;
import pageturners.models.UserPostObject;

public class AddUserPostControl implements ControlBase {
    
    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public AddUserPostControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserPostObject handleAddPost(int categoryId, String title, String postContents) {
        UserObject user = loginControl.getUserObject();

        if (user == null) {
            return null;
        }

        if (user.isMuted) {
            return null;
        }

        if (title == null || title.length() == 0) {
            return null;
        }

        if (postContents == null || postContents.length() == 0 || postContents.length() > 1024) {
            return null;
        }

        return databaseManager.addPost(categoryId, user.id, title, postContents);
    }
}
