package nicos.model.roulette;

import java.util.Random;

public class RouletteModel implements IRouletteModel{
    private int number;
    private int min=0;
    private int max=36;
    public RouletteModel(){}

    @Override
    public Integer getNumber(){
        return number;
    }

    @Override
    public void generateNumber(){
        Random random = new Random();
        number = random.nextInt((max - min) + 1) + min;
    }
}
