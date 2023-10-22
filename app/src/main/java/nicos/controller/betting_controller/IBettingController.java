package nicos.controller.betting_controller;

import java.util.HashMap;

import nicos.controller.IController;
import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;
import nicos.view.components.IComponent;

public interface IBettingController extends IController{
    public HashMap<String, IComponent> getComponents();
    public void setConfig();
    public void addController(String key, IController controller);
    boolean hasWinner(String winningNumber);
    public void addBet(String idUser, String nameNode, CasinoChip chips);
    public HashMap<String, ICasinoChip> claimBet(String winningNumber);
    public void calculatePrizes(String winningNumber);
    public void newRound();
}
