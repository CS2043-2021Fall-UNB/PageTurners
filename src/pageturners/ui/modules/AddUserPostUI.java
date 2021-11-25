package pageturners.ui.modules;

import pageturners.controls.AddUserPostControl;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserPostObject;

public class AddUserPostUI {

    private final AddUserPostControl addUserPostControl;
    private final UserCategoryObject category;

    public AddUserPostUI(AddUserPostControl addUserPostControl, UserCategoryObject category) {
        this.addUserPostControl = addUserPostControl;
        this.category = category;
    }

    public void displayAddPostForm() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void clickAddPost() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayAddPostConfirmation(UserPostObject post) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
