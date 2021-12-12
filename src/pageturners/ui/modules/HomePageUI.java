package pageturners.ui.modules;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pageturners.ui.UIElement;

public class HomePageUI extends UIElement {

    public HomePageUI() {
        VBox main = new VBox();

        Label header = new Label("Welcome to Page Turners!");
        header.setFont(Font.font(30));

        Label body = new Label("PageTurners is a forum where you can involve yourself in book discussion.\n\n" +
            "Click the Forums button to view posts and categories.\n" +
            "If you are a member, or you'd like to become one, click the Login and Register button.");

        main.getChildren().setAll(header, body);

        //main.setPadding(new Insets(0, 10, 10, 10));

        show(main);
    }
}
