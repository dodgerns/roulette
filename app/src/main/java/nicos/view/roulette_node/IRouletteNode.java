package nicos.view.roulette_node;

import java.util.HashMap;

import nicos.commons.Callback;
import nicos.view.components.IComponent;

public interface IRouletteNode{
    public HashMap<String, IComponent> getComponents();
    public void setAction(String key, Callback callback);
    public void changeState(String key, String newState);
    public void addComponent(String key, IComponent component);
}