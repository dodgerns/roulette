package nicos.controller.game_controller;

import java.util.HashMap;

import nicos.controller.IController;
import nicos.controller.roulette_controller.IRoulleteController;
import nicos.model.game.IGameModel;
import nicos.view.components.IComponent;
import nicos.view.game_node.IGameNode;


public class GameController implements IGameController {
    private HashMap<String, IController> controllers;
    private IGameModel gameModel;
    private IGameNode gameNode;

    public GameController(){
        controllers = new HashMap<>();
    }
    public GameController(IGameModel gameModel, IGameNode gameNode){
        controllers = new HashMap<>();
        this.gameModel = gameModel;
        this.gameNode = gameNode;
    }

    @Override
    public HashMap<String, IComponent> getComponents() {
        HashMap<String, IComponent> components = new HashMap<>();
        components.putAll(gameNode.getComponents());
        for (IController controller : controllers.values()) {
            components.putAll(controller.getComponents());
        }
        return components;
    }

    @Override
    public void setConfig() {
        gameNode.setAction("startGame", ()->startGame());
    }

    @Override
    public void addController(String key, IController Controller) {
        controllers.put(key, Controller);
    }

    public void startGame(){
        gameNode.changeState("startGame", "Jugando");
        IRoulleteController rouletteController = (IRoulleteController) controllers.get("RouletteController");
        rouletteController.spinRoulette();
        String winningNumber = rouletteController.getStatus().get("WinningNumber");
        System.out.println(winningNumber);
    }
}
