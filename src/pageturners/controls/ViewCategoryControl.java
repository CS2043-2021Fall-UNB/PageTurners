package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserPostObject;

public class ViewCategoryControl implements ControlBase {

    private final DatabaseManager databaseManager;

    public ViewCategoryControl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
    
    public UserCategoryObject[] handleViewCategories() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserPostObject[] handleViewCategory(int categoryId){
        throw new UnsupportedOperationException("Not implemented");

        UserPostObject[] postObject = databaseManager.getPost(categoryId);

        return postObject;
    }
}
