package dlv;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("puntataMinima")
public class puntataMinima {

    @Param(0)
    private int value;

    public puntataMinima(){}

    public puntataMinima(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
