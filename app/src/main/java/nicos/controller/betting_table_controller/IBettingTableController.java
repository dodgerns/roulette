package nicos.controller.betting_table_controller;


public interface IBettingTableController{

    
    Boolean hasWinner(String winningNumber);
    public void claimBet(String winningNumber);
    void calculatePrizes(String winningNumber);
    void userState(String nameUser);
    public void blockBettingTable();
    public void unblockBettingTable();
}
