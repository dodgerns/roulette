package nicos.controller.betting_table_controller;

import java.util.HashMap;

import nicos.controller.IController;
import nicos.model.betting_table.IBettingTableModel;
import nicos.view.betting_table_node.IBettingTableNode;
import nicos.view.components.IComponent;

public class BettingTableController implements IBettingTableController{

    private HashMap<String, IController> controllers;
    private IBettingTableModel bettingTableModel;
    private IBettingTableNode bettingTableNode;

    public BettingTableController(){
        controllers = new HashMap<>();
    }
    public BettingTableController(IBettingTableModel bettingTableModel, IBettingTableNode bettingTableNode){
        controllers = new HashMap<>();
        this.bettingTableModel = bettingTableModel;
        this.bettingTableNode = bettingTableNode;
    }

    @Override
    public HashMap<String, IComponent> getComponents() {
        HashMap<String, IComponent> components = new HashMap<>();
        components.putAll(bettingTableNode.getComponents());
        for (IController controller : controllers.values()) {
            components.putAll(controller.getComponents());
        }
        return components;
    }

    @Override
    public void setConfig() {
        for (String key : bettingTableNode.getComponents().keySet()) {
            bettingTableNode.setAction(key, ()->switchState(key));
        }
    }

    @Override
    public void addController(String key, IController Controller) {
        controllers.put(key, Controller);
    }

    private void switchState(String nameNode){
        bettingTableNode.changeState(nameNode, "●");
    }
}
