package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserCommentObject;

public class DeleteCommentControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;
 
    public DeleteCommentControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserCommentObject handleDeleteComment(int commentId) {
        if (loginControl.getAdminObject() == null) {
            return null;
        }

        return databaseManager.deleteComment(commentId);
    }
}
