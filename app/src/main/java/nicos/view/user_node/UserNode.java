package nicos.view.user_node;

import nicos.view.ANode;
import nicos.view.components.IComponent;

public class UserNode extends ANode implements IUserNode{

    public UserNode(){
        super();
    }

    @Override
    public void changeState(String key, String newState) {
        IComponent component = components.get(key);
        component.changeText(newState);
    }
    
}
