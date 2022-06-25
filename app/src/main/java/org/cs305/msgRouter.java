package org.cs305;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.websocket.WsContext;

import javax.swing.plaf.nimbus.State;
import java.io.FileWriter;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

public class msgRouter {
        Connection con = null;
        Javalin app;
        String host;
        int port;
        String db_url;
        String log_file;
        msgRouter() {
            // parse json file
            jsonParser js = new jsonParser();
            host = js.host;
            port = js.port;
            db_url = js.db_url;
            log_file = js.log_file;
            writeToLog("JSON Parsed\n");

            try {
                // establish connection to RDBMS
                Class.forName("org.sqlite.JDBC");
                //String dbURL = "jdbc:sqlite:/D:\\Course_materials\\CS305\\midsem\\app\\src\\main\\java\\org\\cs305\\messages_db.db";
                String dbURL = db_url;
                con = DriverManager.getConnection(dbURL);
                writeToLog("Connection established to sqlite\n");
            } catch(Exception e){
                e.printStackTrace();
                writeToLog(e.toString());
            }
        }

        // write to log file
    void writeToLog(String msg){
        try {
            FileWriter Writer = new FileWriter(log_file, true);
            Writer.append(msg);
            Writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
            // start server for listening
        void startRouter(){
            app = Javalin.create().start(7070);
            app.get("/", ctx -> ctx.result("Hello World"));
            app.post("/", ctx -> {
                String msg = ctx.body();
                // parse message
                xmlParser parsedMsg = new xmlParser(ctx.body());
                // look routing table
                lookRoutingTable lR = new lookRoutingTable(parsedMsg.Sender, parsedMsg.MessageType, con);
                // write in logs
                LocalDateTime date = LocalDateTime.now();
                String query = "INSERT INTO message_logs(RouteId, EventType, EventTime) values('"+lR.RouteId + "','RECEIVED','"+date.toString()+"');";
                writeToLog("Query executed: "+query);
                Statement stmtR = con.createStatement();
                int res = stmtR.executeUpdate(query);

                var values = new HashMap<String, String>() {{
                    put("Sender", parsedMsg.Sender);
                    put("MessageType", parsedMsg.MessageType);
                    put("MessageUUID", parsedMsg.MessageUUID);
                    put("Body", parsedMsg.Body);
                }};
                // forward message
                var objectMapper = new ObjectMapper();
                String requestBody = objectMapper
                        .writeValueAsString(values);
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(lR.Destination))
                        .POST(HttpRequest.BodyPublishers.ofString(msg))
                        .build();
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());

                String query2 = "INSERT INTO message_logs(RouteId, EventType, EventTime) values('"+lR.RouteId + "','SENT','"+date.toString()+"');";
                Statement stmtS = con.createStatement();
                int res2 = stmtS.executeUpdate(query2);
                ctx.status(201);
            });
        }
}
