package nicos.view.game_pane;

import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import nicos.view.ANode;
import nicos.view.components.IComponent;

public class GamePane extends Pane{

    private HashMap<String, ANode> nodes;

    public GamePane() {
        super();
        setMinSize(1000, 700);
        nodes = new HashMap<>();
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
