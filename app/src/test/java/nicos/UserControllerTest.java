package nicos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nicos.controller.user_controller.IUserController;
import nicos.controller.user_controller.UserController;
import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;
import nicos.model.user.IUserModel;
import nicos.model.user.UserModel;
import nicos.view.user_node.IUserNode;
import nicos.view.user_node.UserNode;

public class UserControllerTest {
    @Test public void userHasEnoughChips() {
        IUserNode userNode = new UserNode();
        IUserModel userModel = new UserModel();
        IUserController userController = new UserController(userModel, userNode);

        ICasinoChip casinoChip = new CasinoChip();
        casinoChip.setChips(100, 10);
        casinoChip.setChips(50, 5);
        userModel.setCasinoChip(casinoChip);

        ICasinoChip betChips = new CasinoChip();
        betChips.setChips(100, 10);

        assertTrue(userController.hasEnoughChips(betChips));
    }

    @Test public void userNoHasEnoughChips() {
        IUserNode userNode = new UserNode();
        IUserModel userModel = new UserModel();
        IUserController userController = new UserController(userModel, userNode);

        ICasinoChip casinoChip = new CasinoChip();
        casinoChip.setChips(100, 10);
        casinoChip.setChips(50, 5);
        userModel.setCasinoChip(casinoChip);

        ICasinoChip betChips = new CasinoChip();
        betChips.setChips(100, 11);

        assertFalse(userController.hasEnoughChips(betChips));
    }
}
