package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserCreationResult;
import pageturners.models.UserCreationInfo;

public class CreateAccountControl implements ControlBase {

    private final DatabaseManager databaseManager;

    public CreateAccountControl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public UserCreationResult handleCreateAccount(UserCreationInfo userInfo) {
        UserCreationResult r = new UserCreationResult();

        if (userInfo.username.length() > 16) {
            r.usernameResult = "The username must be less than 16 characters.";
        }
        else if (!checkUsername(userInfo.username)) {
            r.usernameResult = "The username is already in use.";
        }
        else {
            r.usernameResult = null;
        }

        if (!checkPassword(userInfo.password)) {
            r.passwordResult = "The password must be at least 8 characters and less than 64.";
        }
        else {
            r.passwordResult = null;
        }

        if (r.passwordResult == null && r.usernameResult == null) {
            r.userResult = databaseManager.createUser(userInfo);
        }
        else {
            r.userResult = null;
        }

        return r;
    }

    private boolean checkUsername(String username) {
        if (databaseManager.getUser(username) != null) {
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password) {
        if (password.length() > 64 || password.length() < 8) {
            return false;
        }

        return true;
    }
}
