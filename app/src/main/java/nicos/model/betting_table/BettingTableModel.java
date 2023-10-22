package nicos.model.betting_table;

import java.util.HashMap;

import nicos.model.casino_chip.ICasinoChip;

public class BettingTableModel implements IBettingTableModel {
    private Boolean bettingTableIsBlocked;
    private HashMap<String, ICasinoChip> betting;

    public BettingTableModel(){
        bettingTableIsBlocked = false;
        betting = new HashMap<>();
    }

    @Override
    public void addBet(String winningNumber, ICasinoChip bet){
        betting.put(winningNumber, bet);
    }

    @Override
    public ICasinoChip getBet(String winningNumber){
        return betting.get(winningNumber);
    }

    @Override
    public HashMap<String, ICasinoChip> getBets(String winningNumber, ICasinoChip bet){
        return betting;
    }

    @Override
    public Boolean hasWinner(String winningNumber) {
        return betting.containsKey(winningNumber);
    }

    @Override
    public boolean isBlocked() {
        return bettingTableIsBlocked;
    }

    @Override
    public void blockBetting() {
        bettingTableIsBlocked = true;
    }
    @Override
    public void unblockBetting() {
        bettingTableIsBlocked = false;
    }
    
}
