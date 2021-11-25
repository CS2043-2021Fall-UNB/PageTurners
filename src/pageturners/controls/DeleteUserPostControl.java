package pageturners.controls;

import pageturners.database.DatabaseManager;

public class DeleteUserPostControl {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public DeleteUserPostControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public boolean handleDeletePost(int postId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
