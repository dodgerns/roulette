package nicos.controller.betting_controller;

import java.util.HashMap;

import nicos.controller.AController;
import nicos.model.betting.IBettingModel;
import nicos.model.casino_chip.ICasinoChip;
import nicos.view.components.IComponent;
import nicos.view.betting_node.IBettingNode;

public class BettingController extends AController implements IBettingController{
    private IBettingNode bettingNode;
    private IBettingModel bettingModel;

    public BettingController(IBettingModel bettingModel, IBettingNode bettingNode){
        super();
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
    public Boolean hasWinner(String winningNumber) {
        return bettingModel.hasWinner(winningNumber);
    }

    @Override
    public void addBet(String idUser, String winningNumber, ICasinoChip chips) {
        bettingModel.addBet(idUser, winningNumber, chips);
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
