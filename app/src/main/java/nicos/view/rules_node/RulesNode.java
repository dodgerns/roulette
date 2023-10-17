package nicos.view.rules_node;

import java.util.HashMap;

import nicos.commons.Callback;
import nicos.view.components.IComponent;

public class RulesNode implements IRulesNode{
    private HashMap<String, IComponent> components;

    public RulesNode(){
        components = new HashMap<>();
    }
    @Override
    public void addComponent(String key, IComponent component) {
        components.put(key, component);
    }

    @Override
    public void addComponents(HashMap<String, IComponent> components) {
        this.components = components;
    }

    @Override
    public void setComponents(HashMap<String, IComponent> components) {
        this.components.putAll(components);
    }

    @Override
    public HashMap<String, IComponent> getComponents() {
        return components;
    }

    @Override
    public void changeState(String key, String newState) {
        IComponent component = components.get(key);
        component.changeText(newState);
    }

    @Override
    public void setAction(String key, Callback callback) {
        IComponent component = components.get(key);
        component.setAction(callback);
    }
    
}
