package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserPostContentObject;

public class ViewPostControl implements ControlBase {

    private final DatabaseManager databaseManager;

    public ViewPostControl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
    
    public UserPostContentObject handleViewPost(int postId) {
        throw new UnsupportedOperationException("Not implemented");

        UserPostContentObject postContent = databaseManager.getPostContent(postId);

        return postContent;
    }
}
