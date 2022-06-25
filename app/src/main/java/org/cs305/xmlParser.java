package org.cs305;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class xmlParser {
    public String Sender;
    public String MessageType;
    public String MessageUUID;
    public String Body;

    xmlParser(String txt) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new InputSource(new StringReader(txt)));
            Sender = document.getElementsByTagName("Sender").item(0).getTextContent();
            MessageType = document.getElementsByTagName("MessageType").item(0).getTextContent();
            MessageUUID = document.getElementsByTagName("MessageUUID").item(0).getTextContent();
            Body = document.getElementsByTagName("Body").item(0).getTextContent();
            System.out.println(Sender);
            System.out.println(MessageType);
            System.out.println(MessageUUID);
            System.out.println(Body.trim());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
