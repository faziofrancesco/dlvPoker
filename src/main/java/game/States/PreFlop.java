package game.States;

import dlv.Budget;
import dlv.WebProbability888;
import game.WebConnector;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class PreFlop extends State {

    public PreFlop(WebConnector connector, WebProbability888 probability) {
        super(connector, probability);
        stati="preflop";
        changeprogram();
    }


    @Override
    protected void takeCards() {
        firstCardPlayer=driver.getFirstCard();
        secondCardPlayer=driver.getSecondCard();
        communitycards=new ArrayList<>();
    }
}
