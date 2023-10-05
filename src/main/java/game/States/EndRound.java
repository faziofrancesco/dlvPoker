package game.States;

import dlv.WebProbability888;
import game.WebConnector;

public class EndRound  extends  State{
    public EndRound(WebConnector connector, WebProbability888 probability) {
        super(connector, probability);
    }

    @Override
    public void execute() {
        driver.clickNextTurn();

    }
}
