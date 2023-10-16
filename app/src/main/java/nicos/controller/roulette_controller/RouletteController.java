package nicos.controller.roulette_controller;

import nicos.controller.IController;
import nicos.model.roulette.IRouletteModel;
import nicos.view.components.IComponent;
import nicos.view.roulette_node.IRouletteNode;

import java.util.HashMap;


public class RouletteController implements IRoulleteController{
    private HashMap<String, IController> controllers;
    private IRouletteModel rouletteModel;
    private IRouletteNode rouletteNode;

    public RouletteController(IRouletteModel rouletteModel, IRouletteNode rouletteNode){
        this.controllers = new HashMap<>();
        this.rouletteModel = rouletteModel;
        this.rouletteNode = rouletteNode;
    }

    public void addComponent(String key, IComponent component){
        rouletteNode.addComponent(key, component);
    }

    public HashMap<String, IComponent> getComponents(){
        return rouletteNode.getComponents();
    }

    public void setConfig(){
    }

    @Override
    public void spinRoulette(){
        rouletteModel.generateNumber();
        rouletteNode.changeState("messageWinningNumber", rouletteModel.getNumber().toString());
    }

    @Override
    public void addController(String key, IController controller) {
        controllers.put(key, controller);
    }

    @Override
    public HashMap<String, String> getStatus(){
        HashMap<String, String> statusRoulette = new HashMap<String, String>();
        statusRoulette.put("WinningNumber", rouletteModel.getNumber().toString());
        return statusRoulette;
    }
}
