package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserCommentObject;
import pageturners.models.UserObject;

public class AddUserCommentControl implements ControlBase {
    
    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public AddUserCommentControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserCommentObject handleAddComment(int postId, String commentContents) {
        if (commentContents.length() == 0 || commentContents.length() > 512) {
            return null;
        }

        UserObject user = loginControl.getUserObject();

        if (user == null) {
            return null;
        }

        return databaseManager.addComment(postId, user.id, commentContents);
    } 
}
