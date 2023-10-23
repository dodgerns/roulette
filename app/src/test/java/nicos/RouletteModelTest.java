package nicos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nicos.model.roulette.IRouletteModel;
import nicos.model.roulette.RouletteModel;

public class RouletteModelTest {
    @Test public void rouletteGiveValuesbettewn1and36() {
        IRouletteModel rouletteModel = new RouletteModel();
        rouletteModel.generateNumber();
        
        rouletteModel.getNumber();
        assertTrue(rouletteModel.getNumber() >= 1 && rouletteModel.getNumber() <= 36);
    }
}
