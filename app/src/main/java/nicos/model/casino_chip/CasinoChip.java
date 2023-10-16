package nicos.model.casino_chip;

public class CasinoChip implements ICasinoChip{
    private int chipAmount;
    private int chipValue;

    public CasinoChip(int chipAmount, int chipValue){
        this.chipAmount = chipAmount;
        this.chipValue = chipValue;
    }

    @Override
    public Integer totalValue(){
        return chipAmount * chipValue;
    }

    @Override
    public Integer getChipAmount(){
        return chipAmount;
    }

    @Override
    public Integer getChipValue(){
        return chipValue;
    }
}
