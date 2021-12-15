package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.SearchCriteria;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserPostObject;
import java.util.*;

public class SearchPostsControl implements ControlBase {

    private final DatabaseManager databaseManager;

    public SearchPostsControl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public ArrayList<UserCategoryObject> handleDisplaySearchForm() {
        return databaseManager.getCategories();
    }

    public ArrayList<UserPostObject> handleSearchPosts(SearchCriteria searchCriteria) {
        if (searchCriteria.keywords.length == 0) {
            return null;
        }

        return databaseManager.getPostsByKeywords(searchCriteria);
    }
}
