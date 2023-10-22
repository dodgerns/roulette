package nicos.model.bet;


public class Bet implements IBet{

    private String chipValue;
    private String chipAmount;
    
    public Bet(){}
    
    public String getChipAmount() {
        return chipAmount;
    }

    public void setChipAmount(String chipAmount) {
        this.chipAmount = chipAmount;
    }

    public String getChipValue() {
        return chipValue;
    }

    public void setChipValue(String chipValue) {
        this.chipValue = chipValue;
    }

}
