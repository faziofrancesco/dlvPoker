package game.States;

import dlv.WebProbability888;
import game.WebConnector;

public class LoseAll extends State {
    public LoseAll(WebConnector web, WebProbability888 prob) {
        super(web,prob);
    }


    @Override
    public void execute() {
        //pausa per vedere se abbiamo vinto o perso prima che si chiuda il programma

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
