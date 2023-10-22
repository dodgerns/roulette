package nicos.view.roulette_node;

import nicos.view.ANode;
import nicos.view.components.IComponent;

public class RouletteNode extends ANode implements IRouletteNode{

    public RouletteNode(){
        super();
    }

    @Override
    public void changeState(String key, String newState) {
        IComponent component = components.get(key);
        component.changeText(newState);
    }
}
