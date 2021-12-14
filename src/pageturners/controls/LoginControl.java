package pageturners.controls;

import java.util.ArrayList;
import java.util.function.Consumer;

import pageturners.database.DatabaseManager;
import pageturners.models.AdminObject;
import pageturners.models.UserObject;

public class LoginControl implements ControlBase {

    private final DatabaseManager databaseManager;

    private UserObject userObject;
    private AdminObject adminObject;

    private ArrayList<Consumer<UserObject>> userLoginCallbacks;
    private ArrayList<Consumer<AdminObject>> adminLoginCallbacks;

    public LoginControl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

        userLoginCallbacks = new ArrayList<Consumer<UserObject>>();
        adminLoginCallbacks = new ArrayList<Consumer<AdminObject>>();
    }

    public UserObject getUserObject() {
        return userObject;
    }

    public void saveUserObject(UserObject userObject) {
        this.userObject = userObject;

        for (Consumer<UserObject> callback : userLoginCallbacks) {
            callback.accept(userObject);
        }
    }

    public AdminObject getAdminObject() {
        return adminObject;
    }

    public void saveAdminObject(AdminObject adminObject) {
        this.adminObject = adminObject;

        for (Consumer<AdminObject> callback : adminLoginCallbacks) {
            callback.accept(adminObject);
        }
    }

    public UserObject handleUserLogin(String username, String password) {
        UserObject user = databaseManager.getUserWithPassword(username, password);

        if (user != null) {
            saveUserObject(user);
        }

        return user;
    }

    public AdminObject handleAdminLogin(String username, String password) {
      AdminObject admin = databaseManager.getAdminWithPassword(username, password);

      if (admin != null) {
          saveAdminObject(admin);
      }

      return admin;
    }

    public void registerUserLoginCallback(Consumer<UserObject> callback) {
        userLoginCallbacks.add(callback);
    }

    public void registerAdminLoginCallback(Consumer<AdminObject> callback) {
        adminLoginCallbacks.add(callback);
    }
}
