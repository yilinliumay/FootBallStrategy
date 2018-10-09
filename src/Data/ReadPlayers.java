package Data;

import javax.xml.parsers.*;

import FootballField.Model.Player;
import org.xml.sax.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import  javax.xml.stream.*;

public class ReadPlayers {


    private NodeList nodeList;

    public ReadPlayers(){

        DOMParser domParser=new DOMParser("/Users/mayliu/IdeaProjects/FootBallStrategy/src/Data/players.xml");
        nodeList=domParser.readFile();
    }

  /* proof: what you input(eg: player ID, part of name)
     fromTag: The tag what you input
     toTag : The tag what you want to get
     For example: you have the ID of a player and want to get his name.
     Then proof=player ID  fromTag="ID"  toTag="name";
  */
   private String GetInfo(String proof, String fromTag, String toTag){

        String playerName=" ";

        if (nodeList == null)  return null;
        for (int i = 0; i <  nodeList.getLength(); i++) {
            Node child_node = nodeList.item(i);
            // find element whose id equals players ID
            if  (child_node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element)child_node;
                String string=el.getElementsByTagName(fromTag).item(0).getTextContent();

                if (string.contains(proof)){
                    playerName=el.getElementsByTagName(toTag).item(0).getTextContent();

                }
            }
        }
        return playerName;
    }

    // get name by ID
    public String GetName(String playerID){
        return GetInfo(playerID,"ID","name");
    }

    // get ID by part of name
    public String GetID(String partName){
        return GetInfo(partName,"name","ID");
    }


}

