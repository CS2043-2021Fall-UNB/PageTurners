package pageturners.ui.helpers;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    
    public static void showInfo(String title, String message) {
        
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setGraphic(null);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
    
    public static boolean showYesNo(String title, String message) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setGraphic(null);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.YES;
    }
}
