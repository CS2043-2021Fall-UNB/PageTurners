package pageturners.ui.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHelper {
    
    public static void showWarning(String title, String message) {
        
        Alert alert = new Alert(AlertType.WARNING);

        alert.setGraphic(null);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
