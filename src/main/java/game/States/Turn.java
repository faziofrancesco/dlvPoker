package game.States;

import dlv.WebProbability888;
import game.WebConnector;

public class Turn extends State {

    public Turn(WebConnector connector, WebProbability888 probability) {
        super(connector, probability);
        stati="turn";
        changeprogram();
    }



}
