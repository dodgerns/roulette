package nicos.model.user;

import java.util.Map;

import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;

public class UserModel implements IUserModel{
    private ICasinoChip casinoChip;

    public UserModel(){
    }

    public void setCasinoChip(ICasinoChip casinoChip){
        this.casinoChip = casinoChip;
    }

    public Boolean hasAmountChip(int chipValue, int chipAmount){
        return casinoChip.hasChipAmount(chipValue, chipAmount);
    }

    @Override
    public boolean hasEnoughChips(CasinoChip chips) {
        return casinoChip.hasChipAmount(chips);
    }

    @Override
    public void takeBet(CasinoChip chipsTaken) {
        casinoChip.takeChips(chipsTaken);
    }

    @Override
    public void claimBet(ICasinoChip chipsWon) {
        casinoChip.claimBet(chipsWon);
    }
    
}
