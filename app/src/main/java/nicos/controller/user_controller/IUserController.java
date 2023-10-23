package nicos.controller.user_controller;

import nicos.model.casino_chip.ICasinoChip;

public interface IUserController{
    public Boolean hasEnoughChips(ICasinoChip chips);
    public void takeBet(ICasinoChip chipsTaken);
    public void changeId(String newId);
    public String getId();
    public Boolean hasId(String userId);
    public void claimBet(ICasinoChip chipsWon);
    public void actualState();
}
