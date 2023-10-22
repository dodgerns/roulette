package nicos.model.user;

import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;

public interface IUserModel {
    public void setCasinoChip(ICasinoChip casinoChip);
    public Boolean hasAmountChip(int chipValue, int chipAmount);
    public boolean hasEnoughChips(CasinoChip chips);
    public void takeBet(CasinoChip chipsTaken);
    public void claimBet(ICasinoChip chipsWon);
}
