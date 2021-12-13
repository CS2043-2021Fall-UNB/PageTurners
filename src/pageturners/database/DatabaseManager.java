package pageturners.database;

import java.sql.*;
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

    public UserObject[] getAllUsers() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserObject updateUserMute(int userId, boolean muteStatus) {
        UserObject user = null;
        Connection connection = null;

        try {
            connection = openConnection();

            PreparedStatement statement = connection.prepareStatement("UPDATE UserRecord SET IsMuted=? WHERE UserId=?; SELECT * FROM UserRecord WHERE UserId=? LIMIT 1;");

            statement.setBoolean(1, muteStatus);
            statement.setInt(2, userId);
            statement.setInt(3, userId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = getUserFromResultSet(result);
            }
        }
        catch (SQLException e) {
            user = null;
            System.err.println("Exception occurred in DatabaseManager.updateUserMute method:\n" + e.toString());
        }
        finally {
            closeConnection(connection);
        }

        return user;
    }

    public UserObject updateModeratorPrivilege(int userId, boolean isModerator) {
        UserObject user = null;
        Connection connection = null;

        try {
            connection = openConnection();

            PreparedStatement statement = connection.prepareStatement("UPDATE UserRecord SET IsMod=? WHERE UserId=?; SELECT * FROM UserRecord WHERE UserId=? LIMIT 1;");

            statement.setBoolean(1, isModerator);
            statement.setInt(2, userId);
            statement.setInt(3, userId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = getUserFromResultSet(result);
            }
        }
        catch (SQLException e) {
            user = null;
            System.err.println("Exception occurred in DatabaseManager.updateModeratorPrivilege method:\n" + e.toString());
        }
        finally {
            closeConnection(connection);
        }

        return user;
    }

    public UserCommentObject deleteComment(int commentId) {
        //Spencers Code
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserCommentObject addComment(int postId, int userId, String commentContents) {
        //Spencer Code
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserPostObject addPost(int categoryId, int userId, String title, String contents) {
        UserPostObject post = null;
        Connection connection = null;

        try {
            connection = openConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO UserPost (CategoryID, UserID, Title, Contents) VALUES (?, ?, ?, ?);");

            statement.setInt(1, categoryId);
            statement.setInt(2, userId);
            statement.setString(3, title);
            statement.setString(4, contents);

            if (statement.executeUpdate() == 0) {
                return null;
            }

            statement = connection.prepareStatement("SELECT * FROM UserPost WHERE PostID=LAST_INSERT_ID() LIMIT 1;");

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                post = getPostFromResultSet(result);
            }
        }
        catch (SQLException e) {
            post = null;
            System.err.println("Exception occurred in DatabaseManager.addPost method:\n" + e.toString());
        }
        finally {
            closeConnection(connection);
        }

        return post;
    }

    public ArrayList<UserPostObject> getPostsByKeywords(SearchCriteria searchCritera) {
        ArrayList<UserPostObject> postList = new ArrayList<UserPostObject>();
        Connection connection = null;

        try {
            connection = openConnection();

            //create query string
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM UserPost");
            
            for (int i = 0; i < searchCritera.keywords.length; i++) {
                if (i == 0) {
                    queryBuilder.append(" WHERE ");
                }
                else {
                    queryBuilder.append(" OR ");
                }

                queryBuilder.append("Title LIKE ?");
            }

            for (int i = 0; i < searchCritera.categories.length; i++) {
                if (i == 0) {
                    queryBuilder.append(" WHERE ");
                }
                else {
                    queryBuilder.append(" OR ");
                }

                queryBuilder.append("Title LIKE ?");
            }

            queryBuilder.append(" ORDER BY PostDate DESC LIMIT 50;");

            String sqlQuery = queryBuilder.toString();
            PreparedStatement st = connection.prepareStatement(sqlQuery);

            for (int i = 0; i < searchCritera.keywords.length; i++) {
                st.setString(i + 1, "%" + searchCritera.keywords[i] + "%");
            }

            //execute SQL query
            ResultSet rs = st.executeQuery();

            //convert retrieved rows to BookInfoObject[]
            
            while (rs.next()) {
                UserPostObject post = new UserPostObject();

                post.id = rs.getInt("ID");
                post.categoryId = rs.getInt("CategoryID");
                post.title = rs.getString("Title");
                post.contents = rs.getString("Contents");
                post.authorId = rs.getInt("AuthorID");
                post.postDate = rs.getTimestamp("PostDate");

                postList.add(post);
            }
        } catch (SQLException e) {
            postList = null;
            System.err.println("Exception occurred in DatabaseManager.getPostsByKeywords method:\n" + e.toString());
        }
        finally {
            closeConnection(connection);
        }

        return postList;
    }

    public ArrayList<UserCategoryObject> getCategories() {
        ArrayList<UserCategoryObject> categories = new ArrayList<UserCategoryObject>();
        try {
            Connection conn = openConnection();
            Statement st = conn.createStatement();

            //create query string
            String sqlQuery = "SELECT * FROM UserCategory;";
            //execute SQL query
            ResultSet rs = st.executeQuery(sqlQuery);

            //convert retrieved rows to UserCategoryObject[]
            
            while (rs.next()) {
                UserCategoryObject category = new UserCategoryObject();

                category.categoryId = rs.getInt("CategoryID");
                category.categoryName = rs.getString("CategoryName");

                categories.add(category);
            }
        } catch (SQLException e) {
            categories = null;
            System.err.println("Exception occurred in DatabaseManager.getCategories method:\n" + e.toString());
        }
        return categories;
    }

    public ArrayList<UserPostObject> getPostsByCategory(int categoryId) {
        ArrayList<UserPostObject> posts = new ArrayList<UserPostObject>();
        try {
            Connection conn = openConnection();

            PreparedStatement st = conn.prepareStatement("SELECT * FROM UserPost WHERE CategoryID=?;");
            st.setInt(1, categoryId);

            //execute SQL query
            ResultSet rs = st.executeQuery();

            //convert retrieved rows to UserPostObject[]
            while (rs.next()) {
                UserPostObject post = new UserPostObject();
                
                post.id = rs.getInt("ID");
                post.categoryId = rs.getInt("CategoryID");
                post.title = rs.getString("Title");
                post.contents = rs.getString("Contents");
                post.authorId = rs.getInt("AuthorID");
                post.postDate = rs.getTimestamp("PostDate");

                posts.add(post);
            }
        } catch (SQLException e) {
            posts = null;
            System.err.println("Exception occurred in DatabaseManager.getPostsByCategory method:\n" + e.toString());
        }
        return posts;
    }

    public UserPostContentObject getPostContent(int postID){
        Connection connection = null;
        UserPostContentObject postContent = null;

        try {
            connection = openConnection();

            PreparedStatement st = connection.prepareStatement("SELECT * FROM UserPost NATURAL JOIN UserRecord WHERE PostID=? LIMIT 1;");

            st.setInt(1, postID);

            postContent = new UserPostContentObject();

            //execute SQL query
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                postContent.userPost = getPostFromResultSet(rs);
                postContent.author = getUserFromResultSet(rs);
            }
            else {
                return null;
            }

            st = connection.prepareStatement("SELECT * FROM UserComment NATURAL JOIN UserRecord WHERE PostID=? LIMIT 200;");

            st.setInt(1, postID);

            rs = st.executeQuery();

            ArrayList<UserCommentObject> commentList = new ArrayList<UserCommentObject>();

            while (rs.next()) {
                UserCommentObject comment = new UserCommentObject();

                comment.id = rs.getInt("CommentID");
                comment.postId = rs.getInt("PostID");
                comment.userId = rs.getInt("UserID");
                comment.contents = rs.getString("Content");
                comment.commentDate = rs.getTimestamp("CommentDate");

                comment.user = getUserFromResultSet(rs);

                commentList.add(comment);
            }

            postContent.userComments = commentList;

        }
        catch (SQLException e) {
            postContent = null;
            System.err.println("Exception occurred in DatabaseManager.getPostContent method:\n" + e.toString());
        }
        finally {
            closeConnection(connection);
        }

        return postContent;
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
        UserObject user = null;
        Connection connection = null;

        try {
            connection = openConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO UserRecord (UserName, Password) VALUES (?, sha1(?));");

            statement.setString(1, userInfo.username);
            statement.setString(2, userInfo.password);

            if (statement.executeUpdate() == 0) {
                return null;
            }

            statement = connection.prepareStatement("SELECT * FROM UserRecord WHERE Username LIKE ? LIMIT 1;");

            statement.setString(1, userInfo.username);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = getUserFromResultSet(result);
            }
        }
        catch (SQLException e) {
            user = null;
            System.err.println("Exception occurred in DatabaseManager.createUser(UserCreationInfo) method:\n" + e.toString());
        }
        finally {
            closeConnection(connection);
        }

        return user;
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

    public UserPostObject deletePost(int postId) {
        Connection connection = null;
        UserPostObject post = null;

        try{
            connection = openConnection();

            PreparedStatement st = connection.prepareStatement("UPDATE UserPost SET Contents=NULL, IsDeleted=TRUE WHERE PostID=?;");

            st.setInt(1, postId);
            
            //execute SQL query
            if(st.executeUpdate() == 0) {
                return null;
            }

            st = connection.prepareStatement("SELECT * FROM UserPost WHERE PostID=?;");

            st.setInt(1, postId);

            ResultSet result = st.executeQuery();

            if(result.next()){
                post = getPostFromResultSet(result);
            }
        }
        catch (SQLException e) {
            post = null;
            System.err.println("Exception occurred in DatabaseManager.deletePostAsUser(int, int) User method:\n" + e.toString());
        }
        finally {
            closeConnection(connection);
        }

        return post;
    }
    
    public boolean deleteUser(int userId) {
        Connection connection = null;

        try{
            connection = openConnection();

            PreparedStatement st = connection.prepareStatement("DELETE FROM UserRecord WHERE UserID=?;");

            st.setInt(1, userId);

            //execute SQL query
            int result = st.executeUpdate();

            return result > 0;
        }
        catch (SQLException e) {
            System.err.println("Exception occurred in DatabaseManager.deleteAccount method:\n" + e.toString());
            return false;
        }
        finally {
            closeConnection(connection);
        }
    }
}
