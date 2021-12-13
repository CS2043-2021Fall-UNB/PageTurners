package pageturners.ui.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pageturners.controls.ControlDirectory;
import pageturners.controls.SearchPostsControl;
import pageturners.models.SearchCriteria;
import pageturners.models.UserCategoryObject;
import pageturners.models.UserPostObject;
import pageturners.ui.UIElement;
import pageturners.ui.helpers.AlertHelper;
import pageturners.ui.main.MainWindowBodyUI;

public class SearchPostsUI extends UIElement {
    private final SearchPostsControl searchPostsControl;
    private final MainWindowBodyUI mainWindowBodyUI;
    private final HashSet<Integer> selectedCategories;

    private TextField searchText;
    private VBox searchResultsLayout;

    public SearchPostsUI(ControlDirectory controlDirectory, MainWindowBodyUI mainWindowBodyUI) {
        this.searchPostsControl = (SearchPostsControl)controlDirectory.getControl(SearchPostsControl.class);
        this.mainWindowBodyUI = mainWindowBodyUI;
        selectedCategories = new HashSet<Integer>();

        displaySearchForm();
    }

    public void displaySearchForm() {
        ArrayList<UserCategoryObject> categories = searchPostsControl.handleDisplaySearchForm();

        displaySearchForm(categories);
    }

    private void displaySearchForm(ArrayList<UserCategoryObject> categories) {
        VBox vLayout = new VBox();
        vLayout.setSpacing(10);

        HBox hLayout = new HBox();
        hLayout.setSpacing(10);

        searchText = new TextField();
        searchText.setOnAction(event -> clickSearch());

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> clickSearch());

        hLayout.getChildren().addAll(searchText, searchButton);

        HBox.setHgrow(searchText, Priority.ALWAYS);

        FlowPane categoryLayout = new FlowPane();
        categoryLayout.setHgap(10);
        categoryLayout.setVgap(5);

        selectedCategories.clear();

        for (UserCategoryObject category : categories) {
            CheckBox checkBox = new CheckBox(category.categoryName);

            checkBox.setOnAction(event -> {
                if (checkBox.isSelected()) {
                    selectedCategories.add(category.categoryId);
                }
                else {
                    selectedCategories.remove(category.categoryId);
                }
            });

            categoryLayout.getChildren().add(checkBox);
        }

        searchResultsLayout = new VBox();
        searchResultsLayout.setSpacing(5);

        vLayout.getChildren().addAll(hLayout, categoryLayout, searchResultsLayout);

        show(vLayout);
    }

    private void clickSearch() {

        SearchCriteria criteria = new SearchCriteria();

        List<String> keywords = Arrays.asList(searchText.getText().split(" "));
        keywords.removeIf(x -> x.length() == 0);

        criteria.keywords = keywords.toArray(new String[0]);
        criteria.categories = selectedCategories.stream().mapToInt(Number::intValue).toArray();

        if (criteria.keywords.length == 0) {
            AlertHelper.showWarning("Search Failed", "Please enter keywords to search with.");
            return;
        }

        ArrayList<UserPostObject> posts = searchPostsControl.handleSearchPosts(criteria);

        if (posts == null || posts.size() == 0) {
            displayNoSearchResults();
            return;
        }

        displaySearchResults(posts);
    }

    private void displaySearchResults(ArrayList<UserPostObject> posts) {
        Label headerLabel = new Label("Search Results:");
        headerLabel.setFont(Font.font(20));

        searchResultsLayout.getChildren().setAll(headerLabel);

        for (UserPostObject post : posts) {
            Button postButton = new Button(post.title + " by " + post.author.username);
            postButton.prefWidthProperty().bind(searchResultsLayout.widthProperty());
            postButton.setStyle("-fx-alignment: BASELINE_LEFT;");
            postButton.setOnAction(event -> mainWindowBodyUI.displayPost(post.id));

            searchResultsLayout.getChildren().add(postButton);
        }
    }

    private void displayNoSearchResults() {
        Label noResultsLabel = new Label("Looks like there aren't any posts here. Why don't you be the first?");
        searchResultsLayout.getChildren().setAll(noResultsLabel);
    }
}
