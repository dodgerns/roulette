package nicos.model.betting_table;

import java.util.HashMap;
import nicos.model.casino_chip.ICasinoChip;
public interface IBettingTableModel {
    public void addBet(String winningNumber, ICasinoChip bet);
    public ICasinoChip getBet(String winningNumber);
    public HashMap<String, ICasinoChip> getBets(String winningNumber, ICasinoChip bet);
    public Boolean hasWinner(String winningNumber);
    public boolean isBlocked();
    public void blockBetting();
    public void unblockBetting();
}
