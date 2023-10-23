package nicos.controller.betting_table_controller;


public interface IBettingTableController{

    public Boolean hasWinner(String winningNumber);
    public void claimBet(String winningNumber);
    public void calculatePrizes(String winningNumber);
    public void userState(String nameUser);
    public void blockBettingTable();
    public void unblockBettingTable();
}
