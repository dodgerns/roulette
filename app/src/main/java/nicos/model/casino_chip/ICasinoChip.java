package nicos.model.casino_chip;

import java.util.HashMap;

public interface ICasinoChip {

    public void setChips(String chipsValueAmount);
    public void setChips(int chipValue, int chipAmount);
    public Integer totalValue();
    public Integer getChipAmount(int chipValue);
    public Boolean existChipValue(int chipValue);
    public Boolean hasChipAmount(int chipValue, int chipAmount);
    public Boolean hasChips();
    public Boolean hasChipAmount(ICasinoChip betChip);
    public HashMap<Integer, Integer> getChips();
    public void takeChips(ICasinoChip chipsTaken);
    public void claimBet(ICasinoChip chipsWon);
    public void multiplyChip(String multiplier);
}
