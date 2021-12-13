package pageturners.controls;

import java.util.ArrayList;

import pageturners.database.DatabaseManager;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserPostObject;

public class ViewCategoryControl implements ControlBase {

    private final DatabaseManager databaseManager;

    public ViewCategoryControl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
    
    public ArrayList<UserCategoryObject> handleViewCategories() {
        return databaseManager.getCategories();
    }

    public ArrayList<UserPostObject> handleViewCategory(int categoryId){
        ArrayList<UserPostObject> postObject = databaseManager.getPostsByCategory(categoryId);

        return postObject;
    }
}
