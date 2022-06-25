package org.cs305;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class jsonParser{
    String host;
    int port;
    String db_url;
    String log_file;

    jsonParser(){
        try {
            // parsing file "config.java"
            Object obj = new JSONParser().parse(new FileReader("D:\\Course_materials\\CS305\\midsem\\app\\src\\main\\java\\org\\cs305\\config.json"));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting host, db_url and log_file values from parsed object
            host = (String) jo.get("host");
            System.out.println(host);
            db_url = (String) jo.get("db_url");
            System.out.println(db_url);
            log_file = (String) jo.get("log_file");
            System.out.println(log_file);

            // getting port from parsed object
            long port = (long) jo.get("port");
            System.out.println(port);
        } catch(Exception e){e.printStackTrace();}
    }
}