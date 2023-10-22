package nicos.view.betting_table_node;

import java.util.HashMap;

import nicos.commons.Callback;
import nicos.view.components.IComponent;

public interface IBettingTableNode {
    public HashMap<String, IComponent> getComponents();
    void newRound(String string);
    public void setAction(String key, Callback callback);
    public void changeState(String key, String newState);
    public void addComponent(String key, IComponent component);
}
