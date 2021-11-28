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
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject handleUpdateUserMute(int userId, boolean muteStatus) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
