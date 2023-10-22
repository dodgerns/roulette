package nicos.controller.game_controller;

import java.util.HashMap;

import nicos.controller.IController;
import nicos.controller.roulette_controller.IRoulleteController;
import nicos.controller.betting_table_controller.BettingTableController;
import nicos.controller.betting_table_controller.IBettingTableController;
import nicos.model.game.IGameModel;
import nicos.view.components.IComponent;
import nicos.view.components.InformationComponent;
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
        gameNode.setAction("StartGame", ()->startGame());
        gameNode.setAction("UserState", ()->userState("UserController"));
        gameNode.setAction("NewRound", ()->newRound());
    }

    public void newRound() {
        gameNode.changeState("StartGame", "Inicar Ronda");
        gameNode.setAction("StartGame", ()->startGame());
        BettingTableController bettingTableController = (BettingTableController) controllers.get("BettingTableController");
        bettingTableController.newRound();
        bettingTableController.unblockBettingTable();
    }

    public void endRound() {
        gameNode.changeState("StartGame", "Ronda Terminada");
        gameNode.setAction("StartGame", ()->{});
        IBettingTableController bettingTableController = (IBettingTableController) controllers.get("BettingTableController");
        bettingTableController.blockBettingTable();
    }

    private void userState(String nameUser) {
        IBettingTableController bettingTableController = (IBettingTableController) controllers.get("BettingTableController");
        bettingTableController.userState(nameUser);
    }

    @Override
    public void addController(String key, IController Controller) {
        controllers.put(key, Controller);
    }

    public void startGame(){
        gameNode.changeState("StartGame", "Jugando");
        IRoulleteController rouletteController = (IRoulleteController) controllers.get("RouletteController");
        rouletteController.spinRoulette();
        String winningNumber = rouletteController.getStatus().get("WinningNumber");
        IBettingTableController bettingTableController = (IBettingTableController) controllers.get("BettingTableController");
        boolean existWinner = bettingTableController.hasWinner(winningNumber);
        if (existWinner) {
            InformationComponent informationComponent = new InformationComponent("Gano", "Existe ganador");
            informationComponent.showMessage();
            bettingTableController.calculatePrizes(winningNumber);
            bettingTableController.claimBet(winningNumber);
        }else{
            InformationComponent informationComponent = new InformationComponent("No hay ganadores", "No hay ganador");
            informationComponent.showMessage();
        }
        endRound();
        System.out.println(winningNumber);
        System.out.println(existWinner);
    }
}
