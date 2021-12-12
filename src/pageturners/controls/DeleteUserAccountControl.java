package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserObject;

public class DeleteUserAccountControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;
       
    public DeleteUserAccountControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public boolean handleDeleteAccount(int userId) {
        UserObject user = loginControl.getUserObject();

        if (user == null) {
            return false;
        }

        if(user.id != userId){
            return false;
        }
        
        return databaseManager.deleteUser(userId);
    }
}
