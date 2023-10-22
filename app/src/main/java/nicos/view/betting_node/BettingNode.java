package nicos.view.betting_node;

import nicos.view.ANode;
import nicos.view.components.IComponent;

public class BettingNode extends ANode implements IBettingNode{
    

    public BettingNode(){
        super();
    }

    @Override
    public void changeState(String key, String newState) {
        IComponent component = components.get(key);
        component.changeText(newState);
    }

}
