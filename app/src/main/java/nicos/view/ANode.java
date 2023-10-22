package nicos.view;

import java.util.HashMap;

import nicos.commons.Callback;
import nicos.view.components.IComponent;

public abstract class ANode {
    protected HashMap<String, IComponent> components;

    public ANode(){
        components = new HashMap<>();
    }

    public void addComponent(String key, IComponent component) {
        components.put(key, component);
    }

    public void addComponents(HashMap<String, IComponent> components) {
        this.components.putAll(components);
    }

    public HashMap<String, IComponent> getComponents() {
        return components;
    }

    public void setComponents(HashMap<String, IComponent> components) {
        this.components = components;
    }

    public void setAction(String key, Callback callback) {
        IComponent component = components.get(key);
        component.setAction(callback);
    }
    
    public IComponent getComponent(String nameComponent) {
        return components.get(nameComponent);
    }

    public abstract void changeState(String key, String newState);
    
}
