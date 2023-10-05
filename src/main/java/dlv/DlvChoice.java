package dlv;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;

public class DlvChoice extends DlvHandler{
    public void setBudget(Budget budget){
        InputProgram facts=new ASPInputProgram();
        try{
            facts.addObjectInput(budget);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        handler.addProgram(facts);
    }



    public void setChanceWin(ProbabilitaVittoria probabilita)
    {

        InputProgram facts=new ASPInputProgram();
        try{
            facts.addObjectInput(probabilita);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        handler.addProgram(facts);
    }
    public void setPrezzoCall(PrezzoCall pc) {
        InputProgram facts= new ASPInputProgram();
        try {
            facts.addObjectInput(pc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.addProgram(facts);
    }

    public void setSceltaAvversario(sceltaAvversario sv) {
        InputProgram facts= new ASPInputProgram();
        try {
            facts.addObjectInput(sv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.addProgram(facts);
    }
    public void setNumeroAvversari(numeroAvversari avv) {
        InputProgram facts= new ASPInputProgram();
        try {
            facts.addObjectInput(avv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.addProgram(facts);
    }
    public void puntataMinima(puntataMinima pm) {
        InputProgram facts= new ASPInputProgram();
        try {
            facts.addObjectInput(pm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.addProgram(facts);
    }

}
