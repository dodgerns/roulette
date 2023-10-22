package nicos.controller;

import java.util.HashMap;

import nicos.view.components.IComponent;


public abstract class AController {
    
    protected HashMap<String, AController> controllers;
    public AController(){
        controllers = new HashMap<>();
    }
    public void addController(String key, AController controller) {
        controllers.put(key, controller);
    }
    public abstract HashMap<String, IComponent> getComponents();
    public abstract void setConfig();
}
