package nicos.view.components;

import javafx.scene.control.TextInputDialog;
import nicos.commons.Callback;

public class DialogComponent extends TextInputDialog{

    public DialogComponent(String text){
        super(text);
        setHeaderText("valor ficha-cantidad fichas: '100-3'"); 
    }

    public void changeText(String newText) {
        setHeaderText(newText);
    }

    public void setAction(Callback callback) {
    }

    public void changeState(){
        showAndWait();
    }
}
