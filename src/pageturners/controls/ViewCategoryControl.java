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
        return databaseManager.getCategories();
    }

    public UserPostObject[] handleViewCategory(int categoryId){

        UserPostObject[] postObject = databaseManager.getPostsByCategory(categoryId);

        return postObject;
    }
}
