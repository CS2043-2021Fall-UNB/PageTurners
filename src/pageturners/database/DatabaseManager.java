package pageturners.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pageturners.models.*;

public class DatabaseManager {
    
    private static Connection openConnection() throws SQLException {
        // In real-world applications, these should not be hard-coded here.
        final String url = "jdbc:mysql://cs2043.cs.unb.ca:3306/cs204301ateam10";
        final String user = "cs204301ateam10";
        final String password = "JYEOmR41";

        return DriverManager.getConnection(url, user, password);
    }

    private static void closeConnection(Connection conn) {
        if (conn == null) {
            return;
        }

        try {
            conn.close();
        } catch (Exception e) {
            System.err.printf("Couldn't close connection: (%s)%n", e.getMessage());
        }
    }

    public UserObject getUser(int userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject[] getAllUsers() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject updateUserMute(int userId, boolean muteStatus) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject updateModeratorPrivilege(int userId, boolean isModerator) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserCommentObject deleteComment(int commentId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserCommentObject addComment(int postId, int userId, String commentContents) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserPostContentObject addPost(int categoryId, int userId, String postContents) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserPostObject[] getPostsByKeywords(SearchCriteria searchCritera) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserCategoryObject[] getCategories() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserPostObject[] getPostsByCategory(int categoryId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserPostContentObject getPostContent(int postID){
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject getUser(String username) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject createUser(UserCreationInfo userInfo) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean deleteUser(int userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject getUserWithPassword(String username, String password) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public AdminObject getAdminWithPassword(String username, String password) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean deletePost(int postId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}