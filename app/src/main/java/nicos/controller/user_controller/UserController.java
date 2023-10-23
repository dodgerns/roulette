package nicos.controller.user_controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import nicos.controller.AController;
import nicos.model.casino_chip.ICasinoChip;
import nicos.model.user.IUserModel;
import nicos.view.components.IComponent;
import nicos.view.components.InformationComponent;
import nicos.view.user_node.IUserNode;

public class UserController extends AController implements IUserController{
    private String id;
    private IUserNode userNode;
    private IUserModel userModel;

    public UserController(IUserModel userModel, IUserNode userNode){
        super();
        Random random = new Random();
        this.id = (random.nextInt(1000) + 0)+"";
        this.userModel = userModel;
        this.userNode = userNode;
    }
    @Override
    public void changeId(String newId){
        this.id = newId;
    }
    @Override
    public String getId(){
        return id;
    }

    @Override
    public HashMap<String, IComponent> getComponents() {
        return userNode.getComponents();
    }

    @Override
    public void setConfig() {
    }

    @Override
    public Boolean hasEnoughChips(ICasinoChip chips) {
        return userModel.hasEnoughChips(chips);
    }

    @Override
    public void takeBet(ICasinoChip chipsTaken) {
        userModel.takeBet(chipsTaken);
    }
    @Override
    public Boolean hasId(String userId) {
        return id.equals(userId);   
    }
    @Override
    public void claimBet(ICasinoChip chipsWon) {
        String message = "Se te agrega:\n";
        for(Map.Entry<Integer, Integer> chip: chipsWon.getChips().entrySet()){
            message += chip.getValue()+" fichas de "+chip.getKey()+"\n";
        }
        InformationComponent informationComponent = new InformationComponent("Ganaste", message);
        informationComponent.showMessage();
        userModel.claimBet(chipsWon);
    }

    @Override
    public void actualState() {
        String message = "Usted tiene:\n";
        for(Map.Entry<Integer, Integer> chip: userModel.getChips().getChips().entrySet()){
            message += chip.getValue()+" fichas de "+chip.getKey()+"\n";
        }
        InformationComponent informationComponent = new InformationComponent("Estado", message);
        informationComponent.showMessage();
    }
}
