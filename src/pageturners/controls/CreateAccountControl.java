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
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean checkUsername(String username) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean checkPassword(String password) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
