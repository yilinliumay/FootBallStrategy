package FootballField.Model;


import java.util.HashSet;

public class Scheme
{
     private String name;
     private HashSet<Player> defenders;
     private HashSet<Player> midFields;
     private HashSet<Player> forwards;
     private HashSet<Player> all;
     private Player goalKeeper;


   public Scheme(String name){
       this.name=name;
       defenders=new HashSet<>();
       midFields=new HashSet<>();
       forwards=new HashSet<>();
       all=new HashSet<>();
   }


   public void addPlayer(Player player, String type) throws DuplicateException {
           int length=all.size();
           all.add(player);
           if (all.size()==length) {
               throw new DuplicateException(player);
           }
           else{
               switch(type){
                   case "defender": defenders.add(player);break;
                   case "midField": midFields.add(player);break;
                   case "forward": forwards.add(player);break;
                   case "goalKeeper": setGoalKeeper(player);
               }
           }
   }


    public double[] ComputeDiscipline(){
        double[] sum=new double[2];

       for(Player player:all){
           sum[0]=player.getAveragePerformance().getDiscipline().getRedCard();
           sum[1]+=player.getAveragePerformance().getDiscipline().getYellowCard();
       }

        sum[0]=sum[0]/all.size();
        sum[1]=sum[1]/all.size();

        return sum;

    }


   public double ComputeAttack(SchemeEnum schemeEnum){
       double sum=0;

       for(Player defender:defenders)
           sum+=defender.getAveragePerformance().getAttack().getAverageAttack()*schemeEnum.defenderPT();

        for(Player forward:forwards)
            sum+=forward.getAveragePerformance().getAttack().getAverageAttack()*schemeEnum.forwardPT();


        for(Player midfield:midFields)
            sum+=midfield.getAveragePerformance().getAttack().getAverageAttack()*schemeEnum.midfieldPT();

        return sum;
    }

    public double ComputeDefend(SchemeEnum schemeEnum){
        double sum=0;

        for(Player defender:defenders)
            sum+=defender.getAveragePerformance().getDefense().getAverageDefense()*schemeEnum.defenderPT();

        for(Player forward:forwards)
            sum+=forward.getAveragePerformance().getDefense().getAverageDefense()*schemeEnum.forwardPT();

        for(Player midfield:midFields)
            sum+=midfield.getAveragePerformance().getDefense().getAverageDefense()*schemeEnum.midfieldPT();

        return sum;
    }


    //Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Player getGoalKeeper() {
        return goalKeeper;
    }

    public void setGoalKeeper(Player goalKeeper) {
        this.goalKeeper = goalKeeper;
    }
}
