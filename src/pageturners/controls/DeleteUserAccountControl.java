package pageturners.controls;

import pageturners.database.DatabaseManager;

public class DeleteUserAccountControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;
       
    public DeleteUserAccountControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public boolean handleDeleteAccount() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
