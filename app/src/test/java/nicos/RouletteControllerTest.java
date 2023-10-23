package nicos;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import nicos.controller.roulette_controller.IRoulleteController;
import nicos.controller.roulette_controller.RouletteController;
import nicos.model.roulette.IRouletteModel;
import nicos.model.roulette.RouletteModel;
import nicos.view.roulette_node.IRouletteNode;
import nicos.view.roulette_node.RouletteNode;

public class RouletteControllerTest {
    @Test public void rouletteControllerHasAWinningNumberKey() {
        IRouletteModel rouletteModel = new RouletteModel();
        IRouletteNode rouletteNode = new RouletteNode();
        IRoulleteController roulleteController = new RouletteController(rouletteModel, rouletteNode);
        roulleteController.spinRoulette();
        HashMap<String, String> winningNumber = roulleteController.getStatus();
        assertTrue(winningNumber.containsKey("WinningNumber"));
    }

    @Test public void rouletteControllerHasAWinningNumberValue() {
        IRouletteModel rouletteModel = new RouletteModel();
        IRouletteNode rouletteNode = new RouletteNode();
        IRoulleteController roulleteController = new RouletteController(rouletteModel, rouletteNode);
        roulleteController.spinRoulette();
        HashMap<String, String> winningNumber = roulleteController.getStatus();
        assertTrue(Integer.parseInt(winningNumber.get("WinningNumber"))>=0);
        assertTrue(Integer.parseInt(winningNumber.get("WinningNumber"))<=36);
    }
    
}
