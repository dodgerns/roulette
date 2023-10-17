package nicos.view.components;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nicos.commons.Callback;

public class MessageComponent extends Text implements IComponent{

    public MessageComponent(String text) {
        super(text);
        //setFont(Font.font("Arial",FontWeight.BOLD, 70));
        setFont(Font.font("Monospaced", 100));
    }

    public MessageComponent(String text, int positionX, int positionY) {
        super(text);
        setX(positionX);
        setY(positionY);
    }

    public MessageComponent(String text, int positionX, int positionY, Color color) {
        super(text);
        setFill(color);
        setX(positionX);
        setY(positionY);
    }

    public void changeText(String newText){
        setText(newText);
    }
    @Override
    public void setAction(Callback callback) {
        setOnMouseClicked((MouseEvent event) -> {
            callback.callback();
        });
    }
}
