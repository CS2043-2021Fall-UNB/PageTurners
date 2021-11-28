package pageturners.controls;

import pageturners.database.DatabaseManager;
import pageturners.models.SearchCriteria;
import pageturners.models.UserPostObject;

public class SearchPostsControl implements ControlBase {

    private final DatabaseManager databaseManager;

    public SearchPostsControl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public UserPostObject[] handleSearchPosts(SearchCriteria searchCritera) {
        throw new UnsupportedOperationException("Not implemented");

        return databaseManager.getPostsByKeywords(searchCritera);
    }
}
