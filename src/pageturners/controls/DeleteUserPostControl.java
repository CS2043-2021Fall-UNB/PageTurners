package pageturners.controls;

import pageturners.database.DatabaseManager;

public class DeleteUserPostControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public DeleteUserPostControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserPostObject handleDeletePost(int postId) {
        if(loginControl.getUserObject() == null){
            return null;
        }
        return databaseManager.deletePostAsUser(postId);
    }
}
