package dlv;

public class Card {
    protected int number;
    protected String seed;

    public Card( String seed,int number ) {
        this.number = number;
        this.seed = seed;
    }

    @Override
    public String toString() {
        StringBuilder s=new StringBuilder("");
        s.append("["+number+" "+seed+"] ");
        return s.toString();
    }

    public int getNumber() {
        return number;
    }

    public String getSeed() {
        return seed;
    }
}
