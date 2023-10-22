package nicos.controller.roulette_controller;

import java.util.HashMap;

public interface IRoulleteController{
    void spinRoulette();
    HashMap<String, String> getStatus();
}
