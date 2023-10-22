package nicos.controller.betting_controller;

import java.util.HashMap;

import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;

public interface IBettingController{
    public void setConfig();
    boolean hasWinner(String winningNumber);
    public void addBet(String idUser, String nameNode, CasinoChip chips);
    public HashMap<String, ICasinoChip> claimBet(String winningNumber);
    public void calculatePrizes(String winningNumber);
    public void newRound();
}
