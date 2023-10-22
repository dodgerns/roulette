package nicos.view.game_node;

import nicos.view.ANode;
import nicos.view.components.IComponent;

public class GameNode extends ANode implements IGameNode {

    public GameNode() {
        super();
    }

    @Override
    public void changeState(String key, String newState) {
        IComponent component = components.get(key);
        component.changeText(newState);
    }

}
