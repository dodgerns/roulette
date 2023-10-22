package nicos.view.game_pane;

import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nicos.view.ANode;
import nicos.view.components.IComponent;

public class GamePane extends Pane{

    private HashMap<String, ANode> nodes;
    private Text mousePositionText;

    public GamePane() {
        super();
        setMinSize(1000, 700);
        nodes = new HashMap<>();
        mousePositionText = new Text(100,100,"");
        mousePositionText.setFill(Color.BEIGE);
        this.getChildren().add(mousePositionText);
        setMousePositionListener();
    }
    private void setMousePositionListener() {
        this.setOnMouseClicked((MouseEvent event) -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            mousePositionText.setText("Mouse Position: X=" + mouseX + ", Y=" + mouseY);
        });
    }
    public void addBackground(String imageUrl){
        String imagePath = getClass().getResource(imageUrl).toExternalForm();
        Image backgroundImage = new Image(imagePath);
        setBackground(new Background(new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        )));
    }

    public void addStage(String key, ANode stage) {
        this.nodes.put(key, stage);
        HashMap<String, IComponent> components= stage.getComponents();
        for (IComponent component : components.values()) {
            Node node = (Node) component;
            this.getChildren().add(node);
        }
    }

    public HashMap<String, ANode> getnodes(){
        return nodes;
    }
}
