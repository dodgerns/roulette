package nicos.model.casino_chip;

import java.util.HashMap;
import java.util.Map;

public class CasinoChip implements ICasinoChip{
    
    //chip value, chip amount
    private HashMap<Integer, Integer> chips;

    public CasinoChip(){
        this.chips = new HashMap<>();
    }

    public CasinoChip(String chipsValueAmount){
        this.chips = new HashMap<>();
        setChips(chipsValueAmount);
    }

    @Override
    public void setChips(String chipsValueAmount){
        String[] chips = chipsValueAmount.split("-");
        if(chips.length==2&& chips[0].matches("\\d+") && chips[1].matches("\\d+")){
            this.chips.put(Integer.parseInt(chips[0]), Integer.parseInt(chips[1]));
        }
    }

    @Override
    public void setChips(int chipValue, int chipAmount){
        this.chips.put(chipValue, chipAmount);
    }

    @Override
    public Integer totalValue(){
        int totalAccount = 0;
        for (Map.Entry<Integer, Integer> chip : chips.entrySet()) {
            int valueChip = chip.getKey();
            int chipAmount = chip.getValue();
            totalAccount += valueChip*chipAmount;
        }
        return totalAccount;
    }

    @Override
    public Integer getChipAmount(int chipValue){
        return chips.get(chipValue);
    }

    @Override
    public Boolean existChipValue(int chipValue){
        return chips.containsKey(chipValue);
    }

    @Override
    public Boolean hasChipAmount(int chipValue, int chipAmount) {
        if(existChipValue(chipValue)){
            int actualChipAmount = chips.get(chipValue);
            return actualChipAmount >= chipAmount;
        }
        return false;
    }

    @Override
    public Boolean hasChipAmount(ICasinoChip betChip) {
        boolean hasAmount = true;
        for(Map.Entry<Integer, Integer> chip: betChip.getChips().entrySet()){
            if(hasChipAmount(chip.getKey(), chip.getValue())){
                hasAmount = hasAmount && (chips.get(chip.getKey()) >= chip.getValue()) && (chips.get(chip.getKey())>0);
            }else{
                hasAmount = false;
            }
        }
        return hasAmount;
    }

    @Override
    public Boolean hasChips() {
        return chips.size()>0;
    }

    @Override
    public HashMap<Integer, Integer> getChips() {
        return chips;
    }

    @Override
    public void takeChips(ICasinoChip chipsTaken) {
        for(Map.Entry<Integer, Integer> chip: chipsTaken.getChips().entrySet()){
            if(hasChipAmount(chip.getKey(), chip.getValue())){
                int newAmount = chips.get(chip.getKey())-chip.getValue();
                chips.put(chip.getKey(), newAmount);
            }
        }
    }

    @Override
    public void claimBet(ICasinoChip chipsWon) {
        for(Map.Entry<Integer, Integer> chip: chipsWon.getChips().entrySet()){
            if (chips.containsKey(chip.getKey())) {
                int amount = chip.getValue();
                int actualAmount = chips.get(chip.getKey());
                chips.put(chip.getKey(), actualAmount + amount);
            }else{
                chips.put(chip.getKey(), chip.getValue());
            }
        }
    }

    @Override
    public void multiplyChip(String multiplier){
        Double multiplierDouble = Double.parseDouble(multiplier);
        for(Map.Entry<Integer, Integer> chip: chips.entrySet()){
            Double prize = chip.getValue() * multiplierDouble;
            chips.put(chip.getKey(), chip.getValue()+prize.intValue());
            if (prize != prize.intValue()){
                Double partDecimal = prize-prize.intValue();
                Double newKey = chip.getKey() * partDecimal;
                if(newKey == newKey.intValue()){
                    chips.put(newKey.intValue(), 1);
                }
            }
        }
    }
}
