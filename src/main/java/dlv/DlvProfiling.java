package dlv;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;

public class    DlvProfiling extends DlvHandler{
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



}
