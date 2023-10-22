package nicos.view.betting_table_node;

import java.util.HashMap;

import nicos.commons.Callback;
import nicos.view.components.IComponent;

public class BettingTableNode implements IBettingTableNode{
    private HashMap<String, IComponent> components;

    public BettingTableNode(){
        components = new HashMap<>();
    }

    @Override
    public void addComponent(String key, IComponent component){
        components.put(key, component);
    }

    @Override
    public HashMap<String, IComponent> getComponents(){
        return components;
    }

    @Override
    public IComponent getComponent(String nameComponent){
        return components.get(nameComponent);
    }
    
    @Override
    public void changeState(String key, String newState){
        IComponent component = components.get(key);
        component.changeText(newState);
    }

    @Override
    public void setAction(String key, Callback callback) {
        IComponent component = components.get(key);
        component.setAction(callback);
    }

    @Override
    public void setComponents(HashMap<String, IComponent> components) {
        this.components = components;
    }

    @Override
    public void addComponents(HashMap<String, IComponent> components) {
        this.components.putAll(components);
    }
}
