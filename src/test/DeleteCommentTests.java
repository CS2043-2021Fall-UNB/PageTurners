package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import pageturners.controls.DeleteCommentControl;
import pageturners.controls.LoginControl;
import pageturners.database.DatabaseManager;
import pageturners.models.AdminObject;
import pageturners.models.UserCommentObject;
import pageturners.models.UserCreationInfo;
import pageturners.models.UserObject;
import pageturners.models.UserPostObject;

public class DeleteCommentTests {
    private DatabaseManager databaseManager;
    private DeleteCommentControl deleteCommentControl;
    private LoginControl loginControl;

    private UserObject user;
    private UserPostObject userPost;
    private UserCommentObject userComment;

    private AdminObject admin;

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
        deleteCommentControl = new DeleteCommentControl(loginControl, databaseManager);

        UserCreationInfo userCreationInfo = new UserCreationInfo();
        userCreationInfo.username = "testing123";
        userCreationInfo.password = testingPassword;

        user = databaseManager.createUser(userCreationInfo);

        int categoryId = databaseManager.getCategories().get(0).categoryId;

        userPost = databaseManager.addPost(categoryId, user.id, "Test Post", "Test Contents");

        userComment = databaseManager.addComment(userPost.id, user.id, "Test");

        admin = null;
    }

    @AfterEach
    void cleanUp() {
        // deleting the user will delete the posts and comments too
        databaseManager.deleteUser(user.id);

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
        UserCommentObject updatedComment = deleteCommentControl.handleDeleteComment(userComment.id);

        assertNull(updatedComment);
    }
    

    @Test
    void testControlSuccess() {
        admin = createAdmin("testingAdmin123", testingPassword);

        assertNotNull(loginControl.handleAdminLogin(admin.username, testingPassword));

        UserCommentObject updatedComment = deleteCommentControl.handleDeleteComment(userComment.id);

        assertNotNull(updatedComment);
        assertTrue(updatedComment.isDeleted);
        assertNull(updatedComment.contents);
    }

    @Test
    void testControlNoComment() {
        admin = createAdmin("testingAdmin123", testingPassword);

        assertNotNull(loginControl.handleAdminLogin(admin.username, testingPassword));

        UserCommentObject updatedComment = deleteCommentControl.handleDeleteComment(-1);

        assertNull(updatedComment);
    }
}
