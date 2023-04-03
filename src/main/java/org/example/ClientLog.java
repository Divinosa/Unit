package org.example;

import com.opencsv.CSVWriter;
import org.json.simple.JSONObject;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class ClientLog implements Serializable {
    private static final long serialVersionUID = 1L;
    JSONObject obj = new JSONObject();
    String[] log;

    public void log(int productNumber, int productCount) {
        obj.put(Integer.toString(productNumber), Integer.toString(productCount));
        System.out.println(obj);
    }

    public void exportAsCSV(String cart, int productCount) throws IOException, ParserConfigurationException, SAXException {
        Shop shop = new Shop("log");
        String str = cart + " " + Integer.toString(productCount);
        log = str.split(" ");
        CSVWriter csvWriter = new CSVWriter(new FileWriter(Shop.fileName, true));
        csvWriter.writeNext(log);
        csvWriter.flush();
        csvWriter.close();
    }
}
