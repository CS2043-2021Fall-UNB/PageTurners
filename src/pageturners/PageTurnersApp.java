package pageturners;

import javafx.application.Application;
import javafx.stage.Stage;
import pageturners.ui.main.MainWindowUI;

public class PageTurnersApp extends Application {
	
	private MainWindowUI _mainWindowUI;

	public PageTurnersApp() {
		_mainWindowUI = new MainWindowUI();
	}

    public static void main(String[] args) {
        launch(args);
    }
	
	public void start(Stage primaryStage) {

		_mainWindowUI.setupStage(primaryStage);
	}
}
