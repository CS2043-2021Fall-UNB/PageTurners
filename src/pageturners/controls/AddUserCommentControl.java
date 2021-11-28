package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserCommentObject;

public class AddUserCommentControl implements ControlBase {
    
    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public AddUserCommentControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserCommentObject handleAddComment(int postId, int userId, String commentContents) {
        throw new UnsupportedOperationException("Not implemented");
    } 
}
