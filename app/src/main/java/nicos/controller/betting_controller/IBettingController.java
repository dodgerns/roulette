package nicos.controller.betting_controller;

import java.util.HashMap;

import nicos.model.casino_chip.ICasinoChip;

public interface IBettingController{
    public void setConfig();
    public Boolean hasWinner(String winningNumber);
    public void addBet(String idUser, String winningNumber, ICasinoChip chips);
    public HashMap<String, ICasinoChip> claimBet(String winningNumber);
    public void calculatePrizes(String winningNumber);
    public void newRound();
}
