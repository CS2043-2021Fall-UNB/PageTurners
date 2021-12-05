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

        if(username.length > 16){
          r.usernameResult = "Username must be less than 16 characters";
        }
        else if(!checkUsername(userInfo.username)){
          r.usernameResult = "Username taken";
        }
        else{
          r.usernameResult = null;
        }

        if(!checkPassword(userInfo.password)){
          r.passwordResult =
          "Password must be at least at least 8 characters & less than 64";
        }
        else{
          r.passwordResult = null;
        }

        if(r.passwordResult == null && r.usernameResult == null){
          databaseManager.createUser(userInfo);
        }

        return r;
    }

    public boolean checkUsername(String username) {
        if(databaseManager.getUser(username) != null){
          return false;
        }
        return true;
    }

    public boolean checkPassword(String password) {
        if(password.length > 64 || password.length < 8){
          return false;
        }

        return true;
    }
}
