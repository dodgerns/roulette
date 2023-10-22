package nicos.controller.betting_controller;

import java.util.HashMap;

import nicos.controller.IController;
import nicos.model.betting.IBettingModel;
import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;
import nicos.view.components.IComponent;
import nicos.view.betting_node.IBettingNode;

public class BettingController implements IBettingController{
    private HashMap<String, IController> controllers;
    private IBettingNode bettingNode;
    private IBettingModel bettingModel;

    public BettingController(IBettingModel bettingModel, IBettingNode bettingNode){
        this.controllers = new HashMap<>();
        this.bettingModel = bettingModel;
        this.bettingNode = bettingNode;
    }

    @Override
    public HashMap<String, IComponent> getComponents() {
        return bettingNode.getComponents();
    }

    @Override
    public void setConfig() {
    }

    @Override
    public void addController(String key, IController controller) {
        controllers.put(key, controller);
    }

    @Override
    public boolean hasWinner(String winningNumber) {
        return bettingModel.hasWinner(winningNumber);
    }

    @Override
    public void addBet(String idUser, String nameNode, CasinoChip chips) {
        bettingModel.addBet(idUser, nameNode, chips);
    }

    @Override
    public HashMap<String, ICasinoChip> claimBet(String winningNumber) {
        return bettingModel.claimBet(winningNumber);
    }

    @Override
    public void calculatePrizes(String winningNumber) {
        bettingModel.calculatePrizes(winningNumber);
    }

    @Override
    public void newRound() {
        bettingModel.newRound();
    }
    
}
