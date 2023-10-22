package nicos.controller.user_controller;

import nicos.controller.IController;
import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;

public interface IUserController extends IController{
    public boolean hasEnoughChips(CasinoChip chips);
    public void takeBet(CasinoChip chipsTaken);
    public void changeId(String newId);
    public String getId();
    public boolean hasId(String userId);
    public void claimBet(ICasinoChip chipsWon);
}
