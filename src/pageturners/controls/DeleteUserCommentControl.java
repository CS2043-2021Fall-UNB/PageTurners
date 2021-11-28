package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserCommentObject;

public class DeleteUserCommentControl implements ControlBase {

    private final LoginControl loginControl;
    private final DatabaseManager databaseManager;

    public DeleteUserCommentControl(LoginControl loginControl, DatabaseManager databaseManager) {
        this.loginControl = loginControl;
        this.databaseManager = databaseManager;
    }

    public UserCommentObject handleDeleteComment() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
