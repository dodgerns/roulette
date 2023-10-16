package nicos.view.roulette_node;

import nicos.commons.Callback;
import nicos.view.components.IComponent;

import java.util.HashMap;

public class RouletteNode implements IRouletteNode{
    private HashMap<String, IComponent> components;

    public RouletteNode(){
        components = new HashMap<>();
    }

    public void addComponent(String key, IComponent component){
        components.put(key, component);
    }

    public HashMap<String, IComponent> getComponents(){
        return components;
    }
    
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
