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

    private UserObject getUserFromResultSet(ResultSet result) throws SQLException {
        UserObject user = new UserObject();

        user.id = result.getInt("UserID");
        user.username = result.getString("Username");
        user.password = result.getString("Password");
        user.accountCreated = result.getTimestamp("AccountCreated");
        user.isMod = result.getBoolean("IsMod");
        user.isMuted = result.getBoolean("IsMuted");

        return user;
    }

    public UserObject getUser(int userId) {
      UserObject user = null;
      Connection connection = null;

      try {
          connection = openConnection();

          PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserRecord WHERE UserID = ? LIMIT 1;");

          statement.setInt(1, userId);

          ResultSet result = statement.executeQuery();

          if (result.next()) {
              user = new UserObject();

              user.id = result.getInt("UserID");
              user.username = result.getString("UserName");
              user.password = result.getString("Password");
              user.accountCreated = result.getTimestamp("AccountCreated");
              user.isMod = result.getBoolean("IsMod");
              user.isMuted = result.getBoolean("IsMuted");
          }
      }
      catch (SQLException e) {
          user = null;
          System.err.println("Exception occurred in DatabaseManager.getUser(int) method:\n" + e.toString());
      }
      finally {
          closeConnection(connection);
      }

      return user;
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
            String sqlQuery = "select * from UserPost where ";
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
            System.err.println("SQL error: getPostsByKeywords");
        }

        return PostList;
    }

    public ArrayList<UserCategoryObject> getCategories() {
        ArrayList<UserCategoryObject> CategoryList = new ArrayList<UserCategoryObject>();
        try {
            Connection conn = openConnection();
            Statement st = conn.createStatement();

            //create query string
            String sqlQuery = "select categoryId, categoryName from UserCategory";
            //execute SQL query
            ResultSet rs = st.executeQuery(sqlQuery);
            //convert retrieved rows to UserCategoryObject[]
            int i = 0;
            while (rs.next()) {
                UserCategoryObject UCate = new UserCategoryObject();
                UCate.categoryId = rs.getInt(1);
                UCate.categoryName = rs.getString(2);
                CategoryList.add(UCate);
                i++;
            }
        } catch (SQLException e) {
            System.err.println("SQL error: getPostsByKeywords");
        }
        return CategoryList;
    }

    public ArrayList<UserPostObject> getPostsByCategory(int categoryId) {
        ArrayList<UserPostObject> PostList = new ArrayList<UserPostObject>();
        try {
            Connection conn = openConnection();
            Statement st = conn.createStatement();

            //create query string
            String sqlQuery = "select * from UserPost where CategoryID = " + categoryId + ";";
            //execute SQL query
            ResultSet rs = st.executeQuery(sqlQuery);
            //convert retrieved rows to UserPostObject[]
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
            System.err.println("SQL error: getPostsByKeywords");
        }
        return PostList;
    }

    public UserPostContentObject getPostContent(int postID){
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject getUser(String username) {
        UserObject user = null;
        Connection connection = null;

        try {
            connection = openConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserRecord WHERE Username LIKE ? LIMIT 1;");

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = getUserFromResultSet(result);
            }
        }
        catch (SQLException e) {
            user = null;
            System.err.println("Exception occurred in DatabaseManager.getUser(int) method:\n" + e.toString());
        }
        finally {
            closeConnection(connection);
        }

        return user;
    }

    public UserObject createUser(UserCreationInfo userInfo) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean deleteUser(int userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject getUserWithPassword(String username, String password) {
      UserObject user = null;
      Connection connection = null;

      try {
          connection = openConnection();

          PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserRecord WHERE UserName LIKE ? AND Password = sha1(?) LIMIT 1;");

          statement.setString(1, username);
          statement.setString(2, password);

          ResultSet result = statement.executeQuery();

          if (result.next()) {
            user = getUserFromResultSet(result);
          }
      }
      catch (SQLException e) {
          user = null;
          System.err.println("Exception occurred in DatabaseManager.getUserWithPassword(String, String) method:\n" + e.toString());
      }
      finally {
          closeConnection(connection);
      }

      return user;
    }

    public AdminObject getAdminWithPassword(String username, String password) {
      AdminObject admin = null;
      Connection connection = null;

      try {
          connection = openConnection();

          PreparedStatement statement = connection.prepareStatement("SELECT * FROM AdminRecord WHERE UserName LIKE ? AND Password = sha1(?) LIMIT 1;");

          statement.setString(1, username);
          statement.setString(2, password);

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
          System.err.println("Exception occurred in DatabaseManager.getAdminWithPassword(String, String) method:\n" + e.toString());
      }
      finally {
          closeConnection(connection);
      }

      return admin;
    }

    public boolean deletePost(int postId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
