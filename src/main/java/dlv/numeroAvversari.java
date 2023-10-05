package dlv;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("avversari")
public class numeroAvversari {

    @Param(0)
    private int value;

    public numeroAvversari(){}

    public numeroAvversari(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}