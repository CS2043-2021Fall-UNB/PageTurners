package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserObject;

public class DeleteAccountControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public DeleteAccountControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserObject[] handleDisplayUsers() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean handleDeleteAccount(int userId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
