package nicos.controller.rules_controller;

import java.util.HashMap;

import nicos.controller.IController;
import nicos.model.rules.IRulesModel;
import nicos.view.components.IComponent;
import nicos.view.rules_node.IRulesNode;

public class RulesController implements IRulesController{
    private HashMap<String, IController> controllers;
    private IRulesNode rulesNode;
    private IRulesModel rulesModel;

    public RulesController(IRulesModel rulesModel, IRulesNode rulesNode){
        this.controllers = new HashMap<>();
        this.rulesModel = rulesModel;
        this.rulesNode = rulesNode;
    }
    @Override
    public HashMap<String, IComponent> getComponents() {
        return rulesNode.getComponents();
    }

    @Override
    public void setConfig() {
    }

    @Override
    public void addController(String key, IController controller) {
        controllers.put(key, controller);
    }
    
}
