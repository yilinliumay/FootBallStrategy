package FootballField.Model;

public class PlayerPerformance {


    private Result result;
    private Attack attack;
    private Defense defense;
    private Discipline discipline;



    public Attack getAttack() {
        return attack;
    }

    // read from games
    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
