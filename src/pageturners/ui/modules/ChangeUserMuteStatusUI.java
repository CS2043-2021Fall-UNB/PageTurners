package pageturners.ui.modules;

import javafx.scene.control.Button;
import pageturners.controls.ChangeUserMuteStatusControl;
import pageturners.controls.ControlDirectory;
import pageturners.models.UserObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;

public class ChangeUserMuteStatusUI extends UIElement {
    
    private final ChangeUserMuteStatusControl changeUserMuteStatusControl;
    private UserObject user;

    public ChangeUserMuteStatusUI(ControlDirectory controlDirectory, UserObject user) {
        this.changeUserMuteStatusControl = (ChangeUserMuteStatusControl)controlDirectory.getControl(ChangeUserMuteStatusControl.class);
        this.user = user;

        displayManageMuteButton();
    }

    public void displayManageMuteButton() {
        Button button;

        if (user.isMuted) {
            button = new Button("Unmute User");
            button.setOnAction(event -> clickUnmuteUser());
        }
        else {
            button = new Button("Mute User");
            button.setOnAction(event -> clickMuteUser());
        }

        show(button);
    }

    private void clickMuteUser() {
        UserObject newUser = changeUserMuteStatusControl.handleUpdateUserMute(user.id, true);

        if (newUser != null) {
            displayMuteConfirmation(newUser);
        }
        else {
            displayMuteFailure();
        }
    }

    private void clickUnmuteUser() {
        UserObject newUser = changeUserMuteStatusControl.handleUpdateUserMute(user.id, false);

        if (newUser != null) {
            displayUnmuteConfirmation(newUser);
        }
        else {
            displayUnmuteFailure();
        }
    }

    private void displayMuteConfirmation(UserObject newUser) {
        user = newUser;
        displayManageMuteButton();

        AlertHelper.showInfo("Mute User Successful", user.username + " has been muted.");
    }

    private void displayMuteFailure() {
        AlertHelper.showWarning("Mute User Failed", "An error occurred while attempting to mute " + user.username + ".");
    }

    private void displayUnmuteConfirmation(UserObject newUser) {
        user = newUser;
        System.out.println(user.isMuted);
        System.out.println(newUser.isMuted);
        displayManageMuteButton();

        AlertHelper.showInfo("Unmute User Successful", user.username + " has been unmuted.");
    }

    private void displayUnmuteFailure() {
        AlertHelper.showWarning("Unmute User Failed", "An error occurred while attempting to unmute " + user.username + ".");
    }
}
