package FootballField.Model;

public enum SchemeEnum {

    //4-4-2
    // Defend 60%   15%
    // MidField 35%  8.75%
    // Forward  5%   2.5%
    Defend442(0.15,0.0875,0.025),

    // Defend 10%  /4  2.5%
    //MidField 30% /4  7.5%
    //Forward 60%  /2  30%
    Attack442(0.025,0.075,0.3),

    //3-4-3

    // Defend 50%  /3   16.6%
    // MidField 40% /4  10%
    // Forward  10% /3  3.3%
    Defend343(0.166,0.1,0.033),

    // Defend 5%  /3  1.6%
    //MidField 25% /4  6.25%
    //Forward 70%  /3  23.3%
    Attack343(0.016,0.062,0.233),

    //4-3-3
    // Defend 60%  /4   15%
    // MidField 30% /3  10%
    // Forward  10% /3  3.3%
    Defend433(0.15,0.1,0.033),

    // Defend 10%  /4  2.5%
    //MidField 20% /3  15%
    //Forward 70%  /3  23.3%
    Attack433(0.025,0.15,0.233),

    //5-3-2
    // Defend 70%  /5  14%
    // MidField 25% /3  8.3%
    // Forward  5% /2  2.5%
    Defend532(0.14,0.083,0.025),

    // Defend 15%  /5  3%
    //MidField 25% /3  8.3%
    //Forward 60%  /2  30%
    Attack532(0.03,0.083,0.3),

    Total(1,1,1);


    private double defenderPT;
    private double midfieldPT;
    private double forwardPT;

    SchemeEnum(double defenderPT,double midfieldPT, double forwardPT){
        this.defenderPT=defenderPT;
        this.forwardPT=forwardPT;
        this.midfieldPT=midfieldPT;
    }

    public  double defenderPT() { return defenderPT; }
    public double midfieldPT() { return midfieldPT; }
    public  double forwardPT() { return forwardPT; }
}
