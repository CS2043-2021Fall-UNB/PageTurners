package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import pageturners.controls.LoginControl;
import pageturners.controls.ManageModeratorControl;
import pageturners.database.DatabaseManager;
import pageturners.models.AdminObject;
import pageturners.models.UserCreationInfo;
import pageturners.models.UserObject;

public class ManageModeratorTests {
    private DatabaseManager databaseManager;
    private ManageModeratorControl manageModeratorControl;
    private LoginControl loginControl;

    private AdminObject admin;

    private UserObject targetUser;

    private final String testingPassword = "Password123!";

    private AdminObject createAdmin(String username, String password) {
        
        AdminObject admin = null;
        Connection connection = null;

        try {
            connection = DatabaseManager.openConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO AdminRecord (UserName, Password) VALUES (?, sha1(?));");

            statement.setString(1, username);
            statement.setString(2, password);

            if (statement.executeUpdate() == 0) {
                return null;
            }

            statement = connection.prepareStatement("SELECT * FROM AdminRecord WHERE Username LIKE ? LIMIT 1;");

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                admin = new AdminObject();
                admin.id = result.getInt("AdminID");
                admin.username = result.getString("UserName");
                admin.password = result.getString("Password");
                admin.accountCreated = result.getTimestamp("AccountCreated");
            }
        }
        catch (SQLException e) {
            admin = null;
            System.err.println("Exception occurred in createAdmin method:\n" + e.toString());
        }
        finally {
            DatabaseManager.closeConnection(connection);
        }

        return admin;
    }

    private boolean deleteAdmin(int adminId) {
        Connection connection = null;

        try{
            connection = DatabaseManager.openConnection();

            PreparedStatement st = connection.prepareStatement("DELETE FROM AdminRecord WHERE AdminID=?;");

            st.setInt(1, adminId);

            //execute SQL query
            int result = st.executeUpdate();

            return result > 0;
        }
        catch (SQLException e) {
            System.err.println("Exception occurred in deleteAdmin method:\n" + e.toString());
            return false;
        }
        finally {
            DatabaseManager.closeConnection(connection);
        }
    }

    @BeforeEach
    void setUp() {
        databaseManager = new DatabaseManager();
        loginControl = new LoginControl(databaseManager);
        manageModeratorControl = new ManageModeratorControl(loginControl, databaseManager);

        UserCreationInfo userCreationInfo = new UserCreationInfo();
        userCreationInfo.username = "testingTarget123";
        userCreationInfo.password = testingPassword;

        targetUser = databaseManager.createUser(userCreationInfo);

        admin = null;
    }

    @AfterEach
    void cleanUp() {
        if (targetUser != null) {
            databaseManager.deleteUser(targetUser.id);
            targetUser = null;
        }
        
        if (admin != null) {
            deleteAdmin(admin.id);
            admin = null;
        }
    }

    // This test ensures the test case's code works prior to performing the actual unit tests
    @Test
    void testCreateAdmin() {
        admin = createAdmin("testingAdmin123", testingPassword);

        assertNotNull(admin);

        boolean deletionResult = deleteAdmin(admin.id);

        assertTrue(deletionResult);

        admin = null;
    }

    @Test
    void testControlNoLogin() {
        boolean result = manageModeratorControl.handleUpdateModeratorPrivilege(targetUser.id, true);

        assertFalse(result);
    }
    
    @Test
    void testControlSuccessAdmin() {
        admin = createAdmin("testingAdmin123", testingPassword);

        assertNotNull(loginControl.handleAdminLogin(admin.username, testingPassword));

        boolean result = manageModeratorControl.handleUpdateModeratorPrivilege(targetUser.id, true);

        assertTrue(result);

        UserObject updatedUser = databaseManager.getUser(targetUser.id);

        assertNotNull(updatedUser);
        assertTrue(updatedUser.isMod);

        result = manageModeratorControl.handleUpdateModeratorPrivilege(targetUser.id, false);

        assertTrue(result);

        updatedUser = databaseManager.getUser(targetUser.id);

        assertNotNull(updatedUser);
        assertFalse(updatedUser.isMod);
    }

    @Test
    void testControlNoUser() {
        admin = createAdmin("testingAdmin123", testingPassword);

        assertNotNull(loginControl.handleAdminLogin(admin.username, testingPassword));

        boolean result = manageModeratorControl.handleUpdateModeratorPrivilege(-1, true);

        assertFalse(result);
    }
}
