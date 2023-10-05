package game.States;

import dlv.WebProbability888;
import game.WebConnector;

public class Flop extends State {

    public Flop(WebConnector connector, WebProbability888 probability) {
        super(connector, probability);
        stati="flop";
        changeprogram();
    }




}
