package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserPostObject;
public class DeletePostControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public DeletePostControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserPostObject handleDeletePost(int postId) {
        if(loginControl.getAdminObject() == null){
            return null;
        }
        return databaseManager.deletePostAsAdmin(postId);
    }
}
