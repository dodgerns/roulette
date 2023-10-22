package nicos.model.betting;

import java.util.HashMap;

import nicos.model.casino_chip.ICasinoChip;

public interface IBettingModel {
    public void addBet(String idUser, String winningNumber, ICasinoChip betChip);
    public Boolean hasWinner(String winningNumber);
    public HashMap<String, ICasinoChip> claimBet(String winningNumber);
    
    public void addPrize(String key, String prize);
    public void addPrizes(HashMap<String, String> prizes);
    public void calculatePrizes(String winningNumber);
    public void newRound();
}
