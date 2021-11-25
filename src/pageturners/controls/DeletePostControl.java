package pageturners.controls;

import pageturners.database.DatabaseManager;

public class DeletePostControl {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;
       
    public DeletePostControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public boolean handleDeletePost(int postId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
