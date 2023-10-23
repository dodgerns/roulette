package nicos;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;
import nicos.model.user.IUserModel;
import nicos.model.user.UserModel;

public class UserModelTest {
    @Test public void userHasEnoughChips() {
        IUserModel userModel = new UserModel();

        ICasinoChip casinoChip = new CasinoChip();
        casinoChip.setChips(100, 10);
        casinoChip.setChips(50, 5);
        userModel.setCasinoChip(casinoChip);
        
        ICasinoChip betChips = new CasinoChip();
        betChips.setChips(100, 10);

        assertTrue(userModel.hasEnoughChips(betChips));
    }
    @Test public void userNoHasEnoughChips() {
        IUserModel userModel = new UserModel();

        ICasinoChip casinoChip = new CasinoChip();
        casinoChip.setChips(100, 10);
        casinoChip.setChips(50, 5);
        userModel.setCasinoChip(casinoChip);

        ICasinoChip betChips = new CasinoChip();
        betChips.setChips(100, 11);

        assertFalse(userModel.hasEnoughChips(betChips));
    }
    @Test public void userNoHasChips() {
        IUserModel userModel = new UserModel();

        ICasinoChip casinoChip = new CasinoChip();
        casinoChip.setChips(100, 10);
        casinoChip.setChips(50, 5);
        userModel.setCasinoChip(casinoChip);

        ICasinoChip betChips = new CasinoChip();
        betChips.setChips(200, 11);

        assertFalse(userModel.hasEnoughChips(betChips));
    }
    @Test public void userTakeChips() {
        IUserModel userModel = new UserModel();

        ICasinoChip casinoChip = new CasinoChip();
        casinoChip.setChips(100, 10);
        casinoChip.setChips(50, 5);
        userModel.setCasinoChip(casinoChip);

        ICasinoChip betChips = new CasinoChip();
        betChips.setChips(100, 7);
        userModel.takeBet(betChips);

        ICasinoChip restChips = new CasinoChip();
        restChips.setChips(100, 3);

        assertTrue(userModel.hasEnoughChips(restChips));
    }
    @Test public void userNoTakeChips() {
        IUserModel userModel = new UserModel();

        ICasinoChip casinoChip = new CasinoChip();
        casinoChip.setChips(100, 10);
        casinoChip.setChips(50, 5);
        userModel.setCasinoChip(casinoChip);

        ICasinoChip betChips = new CasinoChip();
        betChips.setChips(100, 11);
        userModel.takeBet(betChips);

        assertTrue(userModel.getChips().getChipAmount(100)==10);
    }
}
