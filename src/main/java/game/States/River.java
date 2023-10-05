package game.States;

import dlv.WebProbability888;
import game.WebConnector;

public class River extends State {

    public River(WebConnector connector, WebProbability888 probability) {
        super(connector, probability);
        stati="river";
        changeprogram();
    }



}
