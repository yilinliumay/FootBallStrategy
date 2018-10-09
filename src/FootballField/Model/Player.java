package FootballField.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;

import static FootballField.Model.Result.DRAWN;
import static FootballField.Model.Result.LOST;
import static FootballField.Model.Result.WON;

public class Player {

    private final StringProperty ID;
    private final StringProperty name;
    private ArrayList<PlayerPerformance> playerPerformances;


    public Player(String name,String id) {
        this.name = new SimpleStringProperty(name);
        this.ID=new SimpleStringProperty(id);
        playerPerformances=new ArrayList<>();
    }


    public void addPerformance(PlayerPerformance playerPerformance){
        this.playerPerformances.add(playerPerformance);
    }


    // Scheme.java : HashSet<Player>
    // Judge whether has duplicate Players
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {

            return true;
        } else if (obj instanceof Player) {
            Player player = (Player) obj;

            return player.getName().equals(getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 1;
    }


    // get averagePerformance (averageAttack, averageDefense) of ArrayList<PlayerPerformance> playerPerformances
    public PlayerPerformance getAveragePerformance(){

        PlayerPerformance averagePerformance= new PlayerPerformance();
        Attack averageAttack= new Attack();
        Defense averageDefense=new Defense();
        Discipline averageDiscipline=new Discipline();


        double[][] sum = new double[2][6];
        double[] cards=new double[2];

        for(PlayerPerformance playerPerformance:playerPerformances){

            Attack attack=playerPerformance.getAttack();
            Defense defense=playerPerformance.getDefense();
            Discipline discipline=playerPerformance.getDiscipline();

            sum[0][0]+=attack.getDribble();
            sum[0][1]+=attack.getCross();
            sum[0][2]+=attack.getPitching();
            sum[0][3]+=attack.getShooting();
            sum[0][4]+=attack.getHeader();
            sum[0][5]+=attack.getFreeKick();

            sum[1][0]+=defense.getClearance();
            sum[1][1]+=defense.getBroken();
            sum[1][2]+=defense.getForcedGrab();
            sum[1][3]+=defense.getHeaderClearance();
            sum[1][4]+=defense.getInterception();
            sum[1][5]+=defense.getGoalkeeperRescue();

            cards[0]+=discipline.getYellowCard();
            cards[1]+=discipline.getRedCard();

        }

        for(int i=0; i<2;i++){
            for(int j=0;j<6;j++) {
                sum[i][j] = sum[i][j] / playerPerformances.size();
            }
        }

        for(int i=0; i<cards.length;i++){
            cards[i]=cards[i]/playerPerformances.size();
        }

        averageAttack.setDribble(sum[0][0]);
        averageAttack.setCross(sum[0][1]);
        averageAttack.setPitching(sum[0][2]);
        averageAttack.setShooting(sum[0][3]);
        averageAttack.setHeader(sum[0][4]);
        averageAttack.setFreeKick(sum[0][5]);

        averageDefense.setClearance(sum[1][0]);
        averageDefense.setBroken(sum[1][1]);
        averageDefense.setForcedGrab(sum[1][2]);
        averageDefense.setHeaderClearance(sum[1][3]);
        averageDefense.setInterception(sum[1][4]);
        averageDefense.setGoalkeeperRescue(sum[1][5]);

        averageDiscipline.setRedCard(cards[1]);
        averageDiscipline.setYellowCard(cards[0]);

        averagePerformance.setAttack(averageAttack);
        averagePerformance.setDefense(averageDefense);
        averagePerformance.setDiscipline(averageDiscipline);

        return averagePerformance;

    }

    public HashMap<Result,Integer> getResultMap(){

        int[] results=new int[3];
        HashMap<Result,Integer> totalResult=new HashMap<>();

        for(PlayerPerformance playerPerformance:playerPerformances){
            switch (playerPerformance.getResult()){
                case WON:results[0]++; break;
                case DRAWN:results[1]++;break;
                case LOST:results[2]++;
            }
        }
        totalResult.put(WON,results[0]);
        totalResult.put(DRAWN,results[1]);
        totalResult.put(LOST,results[2]);

        return totalResult;
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public ArrayList<PlayerPerformance> getPlayerPerformances() {
        return playerPerformances;
    }

    public void setPlayerPerformances(ArrayList<PlayerPerformance> playerPerformances) {
        this.playerPerformances = playerPerformances;
    }
}
