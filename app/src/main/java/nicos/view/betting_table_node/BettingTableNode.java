package nicos.view.betting_table_node;

import nicos.view.ANode;
import nicos.view.components.IComponent;

public class BettingTableNode extends ANode implements IBettingTableNode{

    public BettingTableNode(){
        super();
    }

    @Override
    public void changeState(String key, String newState){
        IComponent component = components.get(key);
        component.changeText(newState);
    }

    @Override
    public void newRound(String newState) {
        for(IComponent component: components.values()){
            component.changeText(newState);
        }
    }
}
