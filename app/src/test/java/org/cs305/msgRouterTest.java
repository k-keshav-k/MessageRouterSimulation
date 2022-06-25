package org.cs305;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class msgRouterTest {
    @BeforeEach
    void setUp() {
        msgRouter mst = new msgRouter();
        mst.startRouter();
        testClient t1 = new testClient();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void Test1() {
        try {
            Unirest.setTimeouts(0, 0);
            Unirest.post("http://localhost:7070/")
                    .header("Content-Type", "application/xml")
                    .body("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n<Message>\r\n    <!-- It is the service’s endpoint URL. -->\r\n    <Sender>http://localhost:3030/</Sender>\r\n    <!-- A string that defines a message type. -->\r\n    <MessageType>VALIDATE_PAN</MessageType>\r\n    <!-- A  universally unique ID for a message. -->\r\n    <MessageUUID>573fbfa0-97e7-11ec-8fbc-bf1589110003</MessageUUID>\r\n    <!-- This is the main payload of the message.\r\n    Its content will always be a CDATA section. -->\r\n    <Body>\r\n        <![CDATA[\r\n       GOOG,INFY,AAPL\r\n       ]]>\r\n    </Body>\r\n</Message>\r\n")
                    .asString();
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n<Message>\r\n    <!-- It is the service’s endpoint URL. -->\r\n    <Sender>http://localhost:3030/</Sender>\r\n    <!-- A string that defines a message type. -->\r\n    <MessageType>VALIDATE_PAN</MessageType>\r\n    <!-- A  universally unique ID for a message. -->\r\n    <MessageUUID>573fbfa0-97e7-11ec-8fbc-bf1589110003</MessageUUID>\r\n    <!-- This is the main payload of the message.\r\n    Its content will always be a CDATA section. -->\r\n    <Body>\r\n        <![CDATA[\r\n       GOOG,INFY,AAPL\r\n       ]]>\r\n    </Body>\r\n</Message>\r\n";
            Scanner sc = new Scanner(new File("D:\\Course_materials\\CS305\\midsem\\app\\src\\test\\java\\org\\cs305\\testFile.txt"));
            String input;
            StringBuffer sb = new StringBuffer();
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                sb.append(input+"\r\n");
            }
            assertEquals(xml, sb.toString());

        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
