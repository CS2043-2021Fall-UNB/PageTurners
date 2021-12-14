package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserCommentObject;
import pageturners.models.UserObject;

public class DeleteUserCommentControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public DeleteUserCommentControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserCommentObject handleDeleteComment(UserCommentObject comment) {
        UserObject user = loginControl.getUserObject();

        if (user == null) {
            return null;
        }

        if (user.id != comment.userId) {
            return null;
        }

        return databaseManager.deleteComment(comment.id);
    }
}
