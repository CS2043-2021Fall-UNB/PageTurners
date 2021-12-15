package pageturners.controls;

import java.util.ArrayList;

import pageturners.database.DatabaseManager;
import pageturners.models.UserObject;

public class DeleteAccountControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public DeleteAccountControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public boolean handleDeleteAccount(int userId) {
        if (loginControl.getAdminObject() == null) {
            return false;
        }

        if(!databaseManager.deleteUser(userId)){
            return false;
        }

        return true;
    }
}
