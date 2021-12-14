package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserObject;

public class ChangeUserMuteStatusControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;
   
    public ChangeUserMuteStatusControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserObject getUserMuteOptions(int userId) {
        return databaseManager.getUser(userId);
    }

    public UserObject handleUpdateUserMute(int userId, boolean muteStatus) {
        if (loginControl.getAdminObject() == null
            && (loginControl.getUserObject() == null || !loginControl.getUserObject().isMod)) {
            return null;
        }

        return databaseManager.updateUserMute(userId, muteStatus);
    }
}
