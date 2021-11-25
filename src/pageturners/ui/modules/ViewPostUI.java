package pageturners.ui.modules;

import pageturners.controls.ViewPostControl;
import pageturners.models.UserPostContentObject;

public class ViewPostUI {

    private final ViewPostControl viewPostControl;
    private final int postId;

    public ViewPostUI(ViewPostControl viewPostControl, int postId) {
        this.viewPostControl = viewPostControl;
        this.postId = postId;
    }

    public void displayPost() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void displayPostContent(UserPostContentObject postContent) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
