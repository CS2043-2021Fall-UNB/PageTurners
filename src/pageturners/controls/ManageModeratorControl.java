package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserObject;

public class ManageModeratorControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public ManageModeratorControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserObject[] handleViewAllUsers() {
        return databaseManager.getAllUsers();
    }

    public boolean handleUpdateModeratorPrivilege(int userId, boolean isModerator) {
        if (loginControl.getAdminObject() == null) {
            return false;
        }

        return databaseManager.updateModeratorPrivilege(userId, isModerator) != null;
    }
}
