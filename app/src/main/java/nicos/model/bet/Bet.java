package nicos.model.bet;

import java.util.HashMap;

public class Bet implements IBet{
    // chipvalue, chipamount
    private HashMap<String, String> bet;

    public Bet(){
        bet = new HashMap<>();
    }
}
