package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Shop {

    public static boolean active;
    public static int format;
    public static String fileName;
    private String[] params;

    private Document doc;

    public Shop(String param) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(new File("shop.xml"));
        setParams(param);
    }

    private void setParams(String param){
        NodeList nodeList = doc.getDocumentElement().getElementsByTagName(param);
        params = nodeList.item(0).getTextContent().trim().split("\n");
        for (int i = 0; i < params.length; i++){
            params[i] = params[i].trim();
        }
        setActive();
        if (active){
            setFileName();
            if (params.length > 2){
                setFormat();
            }
        }
    }

    private void setActive(){
        active = params[0].equals("true");
    }
    private void setFormat(){
        if (params[2].equals("json")){
            format = 1;
        } else if (params[2].equals("txt")) {
            format = 2;
        } else format = 0;

    }
    private void setFileName(){
        fileName = params[1];
    }
}
