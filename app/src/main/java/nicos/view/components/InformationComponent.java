package nicos.view.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import nicos.commons.Callback;

public class InformationComponent implements IComponent {
    private String title;
    private String message;

    public InformationComponent(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public void changeText(String newText) {
        this.message = newText;
    }

    @Override
    public void setAction(Callback callback) {
    }

    public void showMessage(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    
}
