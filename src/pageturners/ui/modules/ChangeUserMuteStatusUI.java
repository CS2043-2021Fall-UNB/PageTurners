package pageturners.ui.modules;

import pageturners.controls.ChangeUserMuteStatusControl;
import pageturners.models.UserObject;

public class ChangeUserMuteStatusUI {
    
    private final ChangeUserMuteStatusControl changeUserMuteStatusControl;
    private int userId;

    public ChangeUserMuteStatusUI(ChangeUserMuteStatusControl changeUserMuteStatusControl, int userId) {
        this.changeUserMuteStatusControl = changeUserMuteStatusControl;
        this.userId = userId;
    }

    public void displayMuteOptions() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickMuteUser() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickUnmuteUser() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayUserObject(UserObject user) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayMuteConfirmation(UserObject user) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayUnmuteConfirmation(UserObject user) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
