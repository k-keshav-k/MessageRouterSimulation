package org.cs305;

import java.sql.*;

public class lookRoutingTable {
    public String Destination;
    int RouteId;

    lookRoutingTable(String Sender, String MessageType, Connection con){
        try {
            String query = "SELECT * from routing_table where Sender = '" + Sender + "' and MessageType = '" + MessageType + "';";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            //System.out.println(res.getString("Destination"));
            Destination = res.getString("Destination");
            RouteId = res.getInt("RouteId");
        } catch (Exception e){e.printStackTrace();}
    }
}
