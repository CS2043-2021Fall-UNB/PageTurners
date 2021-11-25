package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserPostObject;

public class AddUserPostControl {
    
    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public AddUserPostControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserPostObject handleAddPost(int categoryId, int userId, String postContents) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
