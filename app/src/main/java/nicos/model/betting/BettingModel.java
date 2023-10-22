package nicos.model.betting;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

import nicos.model.casino_chip.ICasinoChip;

public class BettingModel implements IBettingModel{
    private HashMap<String, ICasinoChip> betting;
    private HashMap<String, ICasinoChip> winningBet;
    private HashMap<String, String> betPrize;

    public BettingModel(){
        betting = new HashMap<>();
        winningBet = new HashMap<>();
    }

    @Override
    public void addBet(String idUser, String winningNumber, ICasinoChip betChip) {
        betting.put(idUser+"!"+winningNumber, betChip);
    }

    @Override
    public Boolean hasWinner(String winningNumber) {
        for (Map.Entry<String, ICasinoChip> bet : betting.entrySet()) {
            String betNumbers = bet.getKey().split("!")[1];
            List<String> betNumbersList = Arrays.asList(betNumbers.split("-"));
            if(betNumbersList.contains(winningNumber)){
                return true;
            }
        }
        return false;
    }

    @Override
    public HashMap<String, ICasinoChip> claimBet(String winningNumber) {
        return winningBet;
    }

    @Override
    public void calculatePrizes(String winningNumber) {
        for (Map.Entry<String, ICasinoChip> bet : betting.entrySet()) {
            String key = bet.getKey();
            String betNumbers = bet.getKey().split("!")[1];
            List<String> betNumbersList = Arrays.asList(betNumbers.split("-"));
            if(betNumbersList.contains(winningNumber)){
                winningBet.put(key, bet.getValue());
            }
        }
    }
}
