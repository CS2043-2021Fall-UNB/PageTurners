package pageturners.database;

import java.sql.*;
import java.lang.*;
import java.util.ArrayList;

import pageturners.models.*;

public class DatabaseManager {

    private static Connection openConnection() throws SQLException {
        // In real-world applications, these should not be hard-coded here.
        final String url = "jdbc:mysql://cs2043.cs.unb.ca:3306/cs204301ateam10";
        final String user = "cs204301ateam10";
        final String password = "JYEOmR41";

        Connection conn = null;

        conn = DriverManager.getConnection(url, user, password);

        return conn;
    }

    private static void closeConnection(Connection conn) {
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

    public ArrayList<UserPostObject> getPostsByKeywords(SearchCriteria searchCritera) {
        ArrayList<UserPostObject> PostList = new ArrayList<UserPostObject>();
        try {
            Connection conn = openConnection();
            Statement st = conn.createStatement();

            //create query string
            String sqlQuery = "select * from BookInfo where ";
            for (int i=0; i<searchCritera.keywords.length; i++) {
                if (i < searchCritera.keywords.length - 1)
                    sqlQuery = sqlQuery + "Title like '%" + searchCritera.keywords[i] + "%' or ";
                else sqlQuery = sqlQuery + "Title like '%" + searchCritera.keywords[i] + "%';";
            }

            //execute SQL query
            ResultSet rs = st.executeQuery(sqlQuery);

            //convert retrieved rows to BookInfoObject[]
            int i = 0;
            while (rs.next()) {
                UserPostObject UPost = new UserPostObject();
                UPost.postID = rs.getInt(1);
                UPost.title = rs.getString(2);
                UPost.authorID = rs.getInt(3);
                UPost.date = rs.getTimestamp(4);
                PostList.add(UPost);
                i++;
            }
        } catch (SQLException e) {
            System.err.println("SQL error: getBooksByKeywords");
        }

        return PostList;
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