package nicos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import nicos.controller.betting_controller.BettingController;
import nicos.controller.betting_controller.IBettingController;
import nicos.model.betting.BettingModel;
import nicos.model.betting.IBettingModel;
import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;
import nicos.view.betting_node.BettingNode;
import nicos.view.betting_node.IBettingNode;

public class BettingControllerTest {
    @Test public void bettingControllerNoHasWinner() {
        IBettingModel bettingModel = new BettingModel();
        IBettingNode bettingNode = new BettingNode();
        IBettingController bettingController = new BettingController(bettingModel, bettingNode);

        assertFalse(bettingController.hasWinner("0"));
    }
    @Test public void bettingControllerHasWinner() {
        IBettingModel bettingModel = new BettingModel();
        IBettingNode bettingNode = new BettingNode();
        IBettingController bettingController = new BettingController(bettingModel, bettingNode);
        ICasinoChip casinoChip = new CasinoChip("100-1");

        bettingController.addBet("1", "0", casinoChip);

        assertTrue(bettingController.hasWinner("0"));
    }
    @Test public void bettingControllerCalculatePrizes() {
        IBettingModel bettingModel = new BettingModel();
        IBettingNode bettingNode = new BettingNode();
        IBettingController bettingController = new BettingController(bettingModel, bettingNode);

        bettingModel.addPrize(Integer.toString(1),  Double.toString(35));

        ICasinoChip casinoChip = new CasinoChip("100-1");
        bettingController.addBet("1", "0", casinoChip);

        assertTrue(bettingController.hasWinner("0"));
        bettingController.calculatePrizes("0");
        HashMap<String, ICasinoChip> chips= bettingController.claimBet("0");
        Integer amount = chips.get("1!0").getChipAmount(100);
        assertTrue(amount==36);
    }
}
