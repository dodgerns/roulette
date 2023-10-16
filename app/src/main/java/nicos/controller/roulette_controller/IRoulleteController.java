package nicos.controller.roulette_controller;

import java.util.HashMap;
import nicos.controller.IController;

public interface IRoulleteController extends IController{
    void spinRoulette();
    HashMap<String, String> getStatus();
}
