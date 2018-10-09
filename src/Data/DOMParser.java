package Data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

 class DOMParser {

    private String filepath;
    private Document doc;

    public DOMParser(String filepath){
        this.filepath=filepath;
    }

    public NodeList readFile() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new ErrorHandler() {
                public void error(SAXParseException spe) {
                    System.err.println(spe);
                }

                public void fatalError(SAXParseException spe) {
                    System.err.println(spe);
                }

                public void warning(SAXParseException spe) {
                    System.out.println(spe);
                }
            });
        } catch (ParserConfigurationException pce) {
            System.err.println(pce);
            System.exit(1);
        }


        try {
            doc = db.parse(new File(filepath));
        } catch (SAXException | IOException se) {
            System.err.println(se);
        }
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        return nodeList;
    }

    // get child Elements by input a Element name
    public ArrayList<Node> getChildNodesByName(String name){
       ArrayList<Node> childNodes=new ArrayList<>();
        NodeList nodeList1=doc.getElementsByTagName(name);
        NodeList childList=nodeList1.item(0).getChildNodes();
        if (childList != null) {
            for (int i = 0; i < childList.getLength(); i++) {
                if (childList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    childNodes.add(childList.item(i));
                    //System.out.println(childList.item(i).getNodeName());
                }
            }
        }
        return childNodes;
    }

}
