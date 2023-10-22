package nicos.view.components;

import javafx.scene.control.Button;

import nicos.commons.Callback;

public class ButtonComponent extends Button implements IComponent{
    public ButtonComponent(String text){
        super(text);
    }

    public ButtonComponent(String text, int positionX, int positionY) {
        super(text);
        setLayoutX(positionX);
        setLayoutY(positionY);
    }

    @Override
    public void changeText(String newText) {
        setText(newText);
    }

    @Override
    public void setAction(Callback callback) {
        setOnAction(event -> { callback.callback(); });
    }
}
