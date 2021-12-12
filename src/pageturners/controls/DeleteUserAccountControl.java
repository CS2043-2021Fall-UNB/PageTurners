package pageturners.controls;

import pageturners.database.DatabaseManager;

public class DeleteUserAccountControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;
       
    public DeleteUserAccountControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserObject handleDeleteAccount(int userId, String password) {
        if(loginControl.getUserObject() == null){
            return null;
        }
        return databaseManager.deleteAccountAsUser(userId, password);
    }
}
