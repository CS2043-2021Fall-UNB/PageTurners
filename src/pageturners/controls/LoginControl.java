package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.AdminObject;
import pageturners.models.UserObject;

public class LoginControl implements ControlBase {

    private final DatabaseManager databaseManager;

    private UserObject userObject;
    private AdminObject adminObject;

    public LoginControl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public UserObject getUserObject() {
        return userObject;
    }

    public void saveUserObject(UserObject userObject) {
        this.userObject = userObject;
    }

    public AdminObject getAdminObject() {
        return adminObject;
    }

    public void saveAdminObject(AdminObject adminObject) {
        this.adminObject = adminObject;
    }
    
    public UserObject handleUserLogin(String username, String password) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public AdminObject handleAdminLogin(String username, String password) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
