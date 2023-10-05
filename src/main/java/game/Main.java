package game;
import dlv.*;
import game.States.*;

import java.io.UnsupportedEncodingException;

public class Main {

    static State state;
    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {

       WebConnector game=new WebConnector();
       WebProbability888 prob=new WebProbability888();
       Budget b=new Budget(20000);

       while(!(state instanceof LoseAll)){
            if(!setState(game,prob)) break ;

            state.execute();
       }

        /* numeroAvversari numeroAvversari=new numeroAvversari(2);
        ProbabilitaVittoria probabilitaVittoria=new ProbabilitaVittoria(prob.GetProbabilityVictory());
        puntataMinima puntataMinima=new puntataMinima(300);
        sceltaAvversario sceltaAvversario=new sceltaAvversario("ciccio","call");
        sceltaAvversario sceltaAvversario1=new sceltaAvversario("pasticcio","call");
        DlvChoice dlv=new DlvChoice();
        dlv.setBudget(b);
        dlv.setChanceWin(probabilitaVittoria);
        dlv.setNumeroAvversari(numeroAvversari);
        dlv.puntataMinima(puntataMinima);
       // dlv.setSceltaAvversario(sceltaAvversario);
        dlv.setSceltaAvversario(sceltaAvversario1);
        String result=dlv.runProgram();
        System.out.println(result);
        */

        game.closeWeb();
        prob.closeWeb();

    }


    static boolean setState(WebConnector web, WebProbability888 prob){
        State.StateType s= web.getActualStateOfTheGame();
      //  System.out.println("State  Attuale: "+s);
        switch (s){
            case PREFLOP:
                state=new PreFlop(web,prob);
                break;
            case FLOP:
                state=new Flop(web,prob);
                break;
            case TURN:
                state=new Turn(web,prob);
                break;
            case RIVER:
                state=new River(web,prob);
                break;
            case ENDMATCH:
                state=new EndRound(web,prob);
                break;
            case LOSEALL:
                state=new LoseAll(web,prob);
                break;
            case ERROR:
               // System.out.println("ERRORE NELLO STATE");
                web.closeWeb();
                prob.closeWeb();
                return false;


        }

        return true;
    }

}
