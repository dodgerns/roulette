package nicos.controller;

import java.util.HashMap;

import nicos.view.components.IComponent;

public interface IController {

    HashMap<String, IComponent> getComponents();
    void setConfig();
    void addController(String key, IController rouletteController);
}
