package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserObject;

public class ManageModeratorControl {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public ManageModeratorControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserObject[] handleViewAllUsers() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean handleUpdateModeratorPrivilege(int userId, boolean isModerator) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
