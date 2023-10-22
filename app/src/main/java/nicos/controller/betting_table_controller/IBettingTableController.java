package nicos.controller.betting_table_controller;

import nicos.controller.IController;

public interface IBettingTableController extends IController{

    Boolean hasWinner(String winningNumber);
    public void claimBet(String winningNumber);
    void calculatePrizes(String winningNumber);
}
