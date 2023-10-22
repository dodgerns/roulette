package nicos.view.components;

import javafx.scene.control.TextInputDialog;
import nicos.commons.Callback;

public class TextInputComponent implements IComponent{
    private String title;
    private String headerText;

    public TextInputComponent(String title, String headerText){
        this.title = title;
        this.headerText = headerText;
    }

    @Override
    public void changeText(String newText) {
        headerText = newText;
    }

    @Override
    public void setAction(Callback callback) {
    }

    public String getInput(){
        TextInputDialog textInputComponent;
        textInputComponent = new TextInputDialog(title);
        textInputComponent.setHeaderText(headerText);
        textInputComponent.showAndWait();
        return textInputComponent.getEditor().getText();
    }
}
