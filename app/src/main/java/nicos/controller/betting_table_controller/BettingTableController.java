package nicos.controller.betting_table_controller;

import java.util.HashMap;
import java.util.Map;

import nicos.controller.AController;
import nicos.controller.betting_controller.IBettingController;
import nicos.controller.betting_controller.BettingController;
import nicos.controller.user_controller.IUserController;
import nicos.controller.user_controller.UserController;
import nicos.model.betting_table.IBettingTableModel;
import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;
import nicos.view.betting_table_node.IBettingTableNode;
import nicos.view.components.IComponent;
import nicos.view.components.TextInputComponent;

public class BettingTableController extends AController implements IBettingTableController{

    private IBettingTableModel bettingTableModel;
    private IBettingTableNode bettingTableNode;

    public BettingTableController(){
        super();
    }
    public BettingTableController(IBettingTableModel bettingTableModel, IBettingTableNode bettingTableNode){
        super();
        this.bettingTableModel = bettingTableModel;
        this.bettingTableNode = bettingTableNode;
    }

    @Override
    public HashMap<String, IComponent> getComponents() {
        HashMap<String, IComponent> components = new HashMap<>();
        components.putAll(bettingTableNode.getComponents());
        for (AController controller : controllers.values()) {
            components.putAll(controller.getComponents());
        }
        return components;
    }

    @Override
    public void setConfig() {
        for (String key : bettingTableNode.getComponents().keySet()) {
            bettingTableNode.setAction(key, ()->doBet(key));
        }
    }

    @Override
    public void addController(String key, AController Controller) {
        controllers.put(key, Controller);
    }

    private void doBet(String nameNode){
        if(!bettingTableModel.isBlocked()){
            ICasinoChip chips = getBet();
            IUserController user = (UserController)controllers.get("UserController");
            IBettingController bettingController = (BettingController)controllers.get("BettingController");
            if (chips.hasChips() && user.hasEnoughChips(chips)) {
                user.takeBet(chips);
                String userId= user.getId();
                bettingController.addBet( userId, nameNode, chips);
                bettingTableNode.changeState(nameNode, "●");
            }
        }
    }

    public ICasinoChip getBet(){
        TextInputComponent textInputComponent = new TextInputComponent("Apuesta", "valor ficha-cantidad fichas: '100-3'");
        String betString = textInputComponent.getInput();
        ICasinoChip betChips = new CasinoChip(betString);
        return betChips;
    }

    @Override
    public Boolean hasWinner(String winningNumber) {
        IBettingController bettingController = (BettingController)controllers.get("BettingController");
        return bettingController.hasWinner(winningNumber);
    }

    
    @Override
    public void claimBet(String winningNumber){
        IUserController user = (UserController)controllers.get("UserController");
        IBettingController bettingController = (BettingController)controllers.get("BettingController");
        HashMap<String, ICasinoChip> winningBets = bettingController.claimBet(winningNumber);

        for (Map.Entry<String, ICasinoChip> betsWon  : winningBets.entrySet()) {
            String userId = betsWon.getKey().split("!")[0];
            if(user.hasId(userId)){
                ICasinoChip chipsWon= betsWon.getValue();
                user.claimBet(chipsWon);
            }
        }
    }
    @Override
    public void calculatePrizes(String winningNumber) {
        IBettingController bettingController = (BettingController)controllers.get("BettingController");
        bettingController.calculatePrizes(winningNumber);
    }
    @Override
    public void userState(String nameUser) {
        IUserController user = (UserController)controllers.get("UserController");
        user.actualState();
    }
    public void newRound() {
        IBettingController bettingController = (BettingController)controllers.get("BettingController");
        bettingController.newRound();
        bettingTableNode.newRound("○");
    }
    @Override
    public void blockBettingTable() {
        bettingTableModel.blockBetting();
    }
    @Override
    public void unblockBettingTable() {
        bettingTableModel.unblockBetting();
    }
}
