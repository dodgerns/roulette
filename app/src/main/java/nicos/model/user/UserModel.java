package nicos.model.user;

import nicos.model.casino_chip.ICasinoChip;

public class UserModel implements IUserModel{
    private ICasinoChip casinoChip;

    public UserModel(){
    }

    @Override
    public void setCasinoChip(ICasinoChip casinoChip){
        this.casinoChip = casinoChip;
    }

    @Override
    public Boolean hasAmountChip(int chipValue, int chipAmount){
        return casinoChip.hasChipAmount(chipValue, chipAmount);
    }

    @Override
    public Boolean hasEnoughChips(ICasinoChip chips) {
        return casinoChip.hasChipAmount(chips);
    }

    @Override
    public void takeBet(ICasinoChip chipsTaken) {
        casinoChip.takeChips(chipsTaken);
    }

    @Override
    public void claimBet(ICasinoChip chipsWon) {
        casinoChip.claimBet(chipsWon);
    }
    @Override
    public ICasinoChip getChips(){
        return casinoChip;
    }
}
