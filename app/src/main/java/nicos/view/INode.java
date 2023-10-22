package nicos.view;

import java.util.HashMap;

import nicos.commons.Callback;
import nicos.view.components.IComponent;

public interface INode {
    void addComponent(String key, IComponent component);
    void addComponents(HashMap<String, IComponent> components);
    void setComponents(HashMap<String, IComponent> components);
    public IComponent getComponent(String nameComponent);
    HashMap<String, IComponent> getComponents();
    void changeState(String key, String newState);
    void setAction(String key, Callback callback);
}
