package game;

public class Player {
    String name;
    String budget;
    int position;

    public Player(String name, String budget, int position) {
        this.name = name;
        this.budget = budget;
        this.position=position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
