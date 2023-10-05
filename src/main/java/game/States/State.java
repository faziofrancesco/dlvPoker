package game.States;

import dlv.*;
import game.WebConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class State {
    protected DlvChoice dlv;
    protected DlvProfiling dlvProfiling;
    protected DlvHandler dlvHandler;
    protected WebConnector driver;
    protected int budget;
    protected WebProbability888 probability;
    protected String pathIA;
    protected  boolean init=false;
    protected String sceltaAvversario;
    protected static ArrayList<HashMap<String, Integer>> behaviour;
    protected static int numRound = 0;
    protected static int numPhases = 0;
    protected String  stati=null;

    protected int posstati=0;
    protected String choicePlayer;
    protected String choiceProgram;
    protected  Card firstCardPlayer;
    protected Card secondCardPlayer;
    protected ArrayList<Card>communitycards;
    protected HashMap<Integer, String> choiseAvversari;
    int callCost;
    int numPlayer;
    int playerWithNoChoice;
    int prob;
    protected String Profiling;
    int numPlayerWithChoice;
    public State(WebConnector connector, WebProbability888 probability){
        driver=connector;
        budget=driver.getPlayerBudget();
        this.probability=probability;
        dlvHandler=new DlvHandler();
      //  dlvProfiling= new DlvProfiling();
        dlv=new DlvChoice();
        changemodalita();
    }

    public void execute(){
        //System.out.println("TEST 1");
        takeCards();
        budget=driver.getPlayerBudget();
      //  System.out.println(budget);
        callCost=driver.getCallCost();
        choiseAvversari=driver.getChoices();
        numPlayer=driver.avversariEffettivamenteinGioco();
       // System.out.println("stato di gioco"+stati);
        playerWithNoChoice=driver.playerWithNoChoice();
        prob=getProbabilityWin();
       // System.out.println("TEST 2");
        choicePlayer=DlvChoice();
       // System.out.println("Stato attuale:" +
                //" callCost= "+callCost+" , numPlayers= "+numPlayer+" probabilita: "+prob);
       // System.out.println(firstCardPlayer+"  "+secondCardPlayer);
       // System.out.println("Il player ha scelto : "+choicePlayer);


     //   System.out.println("TEST 3");
        if(choicePlayer.equals("fold")) {
          //  System.out.println("probabilità di "+prob);
            driver.Fold();

        }
        if(choicePlayer.equals("call")) {
            int howmuch=0;
             //   System.out.println("AUMENTO DI "+howmuch +"con probabilità di "+prob);
            driver.scrollBar(howmuch);
            driver.Call();

        }
        if(choicePlayer.equals("raise")){
            // TODO: 11/07/2020 sol temporanea delle 23:10 dopo 2 birre. in base alla percentuale di vittoria punto
            // TODO: 14/07      bisogna riguardare n'attimo le percentuali
            int howmuch=160;
            if(callCost>budget){
                howmuch=0;
            }
            else {
                if (prob >= 45 && prob <= 55)
                    howmuch = 200;
                if (prob >= 56 && prob <= 65)
                    howmuch = 400;
                if (prob >= 66 && prob <= 75)
                    howmuch = 500;
                if (prob >= 75 && prob <= 85)
                    howmuch = 650;
                if (prob >= 86 && prob <= 100)
                    howmuch = 800 ;

              //  System.out.println("AUMENTO DI "+howmuch +"con probabilità di "+prob);
                driver.scrollBar(howmuch);
                driver.Call();
            }

        }

    }

    protected void takeCards() {
        firstCardPlayer=driver.getFirstCard();
        secondCardPlayer=driver.getSecondCard();
        communitycards=driver.getCards();
       // System.out.println(communitycards);
    }
    protected int getProbabilityWin(){

        /*vecchia versione
        numPlayerWithChoice=driver.numPlayerWithChoice();

        //probability.deletefirst();
        int playerInGame=numPlayerWithChoice+driver.playerWithNoChoice();
        for(int i=2;i<2+playerInGame;i++)
        {
            probability.setAvversario(i);
        }
        probability.setPlayerCards(firstCardPlayer,secondCardPlayer);


        int p=probability.GetProbabilityVictory();
        probability.resetTable();
        */
        driver.setNumPlayers();
        int playersNotFold=driver.playerWithNoChoice()+driver.numPlayerWithChoice();
        int player888=probability.numberPlayer888table();
       // System.out.println("Player not fold: "+playersNotFold);
        //System.out.println("Player senza scelta: "+driver.playerWithNoChoice());
        //System.out.println("Player con scelta: "+driver.numPlayerWithChoice());
        //System.out.println("Giocatori al tavolo: "+player888);
        while(player888<playersNotFold-1){
          //  System.out.println("Ho aggiunto!");
            //finchè bisogna aggiugnere al tavolo
            probability.setAvversario(player888);
            player888=probability.numberPlayer888table();
        }
        while(player888>playersNotFold){
            //System.out.println("Ho rimosso!");
            //finchè bisogna rimuovere dal tavolo
            probability.deletefirst();
            player888=probability.numberPlayer888table();
        }

        for(int i=1;i<=communitycards.size();i++)
        {
            probability.setcards(communitycards.get(i-1),i);
        }
        probability.setPlayerCards(firstCardPlayer,secondCardPlayer);
        int p=probability.GetProbabilityVictory();
        probability.resetTable();
        return p;
    }



    //todo in realtà va bene anche il metodo voi , va bene string solo per il testing
    protected String DlvChoice()
    {
        //todo vanno aggiunte le classi no le variabili

        dlv.setBudget(new Budget(budget));
        dlv.setChanceWin(new ProbabilitaVittoria(prob));
      //  System.out.println("la prob è :"+prob);
        dlv.setNumeroAvversari(new numeroAvversari(numPlayer));
        dlv.puntataMinima(new puntataMinima(callCost));
        // dlv.setSceltaAvversario(sceltaAvversario);


        String result=dlv.runProgram();
        return result;
    }
    protected void changemodalita(){

        dlvProfiling=new DlvProfiling();
        dlvProfiling.setProgram("src/main/resources/profiling.txt");
        budget=driver.getPlayerBudget();
        dlvProfiling.setBudget(new Budget(budget));



        Profiling=dlvProfiling.runProgram1();
       // System.out.println("Il profiling è "+Profiling);

    }
    protected void changeprogram(){
        //System.out.println("Il profiling è "+Profiling);
        dlv.setProgram("src/main/resources/"+Profiling+"/"+stati+".txt");
    }
    public enum StateType{PREFLOP, FLOP, TURN, RIVER, ENDMATCH, LOSEALL, ERROR}

}
