package nicos.view.components;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nicos.commons.Callback;

public class OnClickSectorComponent extends Text implements IComponent{

    public OnClickSectorComponent(String text, int positionX, int positionY, int widthRectangle, int heightRectangle) {
        super(text);
        setX(positionX);
        setY(positionY);
        setOnMouseClicked((MouseEvent event) -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            setText("Mouse Position: X=" + mouseX + ", Y=" + mouseY);
        });
    }

    public OnClickSectorComponent(String text, int positionX, int positionY, Color color) {
        super(text);
        setFill(color);
        setX(positionX);
        setY(positionY);
    }

    @Override
    public void changeText(String newText) {
        setText(newText);
    }

    @Override
    public void setAction(Callback callback) {
        setOnMouseClicked((MouseEvent event) -> {
            callback.callback();
        });
    }
    
}
