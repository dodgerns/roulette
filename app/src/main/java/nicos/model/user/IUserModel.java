package nicos.model.user;

import nicos.model.casino_chip.ICasinoChip;

public interface IUserModel {
    public void setCasinoChip(ICasinoChip casinoChip);
    public Boolean hasAmountChip(int chipValue, int chipAmount);
    public Boolean hasEnoughChips(ICasinoChip chips);
    public void takeBet(ICasinoChip chipsTaken);
    public void claimBet(ICasinoChip chipsWon);
    public ICasinoChip getChips();
}
