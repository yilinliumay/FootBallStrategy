package Data;

import javax.xml.parsers.*;

import FootballField.Model.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import  javax.xml.stream.*;

import static FootballField.Model.Result.DRAWN;
import static FootballField.Model.Result.LOST;
import static FootballField.Model.Result.WON;

public class ReadGames {


    private NodeList nodeList;
    private DOMParser domParser;

    public ReadGames(){

        domParser=new DOMParser("/Users/mayliu/IdeaProjects/FootBallStrategy/src/Data/games.xml");
        nodeList=domParser.readFile();
    }

    // get the performances of all games the player attended,  get by player's ID
    public Player getPlayerInfo(String playerID){

        // return value
        Player player=new Player(new ReadPlayers().GetName(playerID),playerID);

        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nodeList.item(i);
                    if (el.getNodeName().equals("Game")) {
                        //GameID : 1, 2 ,3
                        String gameID=el.getAttribute("id");

                        //result
                        String result=el.getElementsByTagName("result").item(0).getTextContent();

                        // <Player>
                        NodeList players=el.getElementsByTagName("player");

                        //player
                        for (int j = 0; j < players.getLength(); j++) {
                            //playerID: CR, LM
                            Element element=(Element)players.item(j);
                            //System.out.println("ID"+element.getAttribute("ID"));
                            // player
                            if(element.getAttribute("ID").equals(playerID)){
                                // attack, defense
                                Attack attack=new Attack();
                                attack.getData(getRecord(element,"attack"));
                                Defense defense=new Defense();
                                defense.getData(getRecord(element,"defense"));
                                Discipline discipline=new Discipline();
                                discipline.getData(getRecord(element,"discipline"));
                                // Performance
                                PlayerPerformance playerPerformance=new PlayerPerformance();
                                playerPerformance.setAttack(attack);
                                playerPerformance.setDefense(defense);
                                playerPerformance.setDiscipline(discipline);
                                playerPerformance.setResult(judgeResult(result));
                                player.addPerformance(playerPerformance);

                            }
                        }

                    }
                }
            }
        }
        return player;
    }

    // get dribble, broken, red card  by inputting corresponding <Game> element
    // and parent element Tag name (eg:"attack","defense","discipline")
    private HashMap<String,Double> getRecord(Element element, String name){

        //    <"dribble", 0.4>
        HashMap<String,Double> record= new HashMap<>();

        ArrayList<Node> nodesList=domParser.getChildNodesByName(name);
        for(Node node:nodesList) {
            String label=element.getElementsByTagName(node.getNodeName()).item(0).getNodeName();
            //System.out.println("label"+label);
            Double content = Double.parseDouble(element.getElementsByTagName(node.getNodeName()).item(0).getTextContent());
           // System.out.println("content"+content);
            record.put(label,content);
        }
        return record;
    }


    // For result of games, change number to Result
    private Result judgeResult(String result){
        switch (result){
            case "1": return WON;
            case "-1": return LOST;
            default: return DRAWN;
        }
    }


}

