package nicos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.HashMap;

import nicos.model.betting.BettingModel;
import nicos.model.betting.IBettingModel;
import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;

public class BettingModelTest {
    @Test public void bettingModelCalculatePrizes() {
        IBettingModel bettingModel = new BettingModel();

        bettingModel.addPrize(Integer.toString(1),  Double.toString(35));

        ICasinoChip casinoChip = new CasinoChip("100-1");
        bettingModel.addBet("1", "0", casinoChip);

        assertTrue(bettingModel.hasWinner("0"));
        bettingModel.calculatePrizes("0");
        HashMap<String, ICasinoChip> chips= bettingModel.claimBet("0");
        Integer amount = chips.get("1!0").getChipAmount(100);
        assertTrue(amount==36);
    }
}
