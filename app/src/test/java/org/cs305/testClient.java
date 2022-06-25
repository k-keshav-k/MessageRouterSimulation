package org.cs305;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;

import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class testClient {
    testClient(){
        var ip = "http://localhost:3030/";
        Javalin app = Javalin.create().start(3030);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.post("/", ctx -> {
            System.out.println(ctx.body());
            FileWriter Writer
                    = new FileWriter("D:\\Course_materials\\CS305\\midsem\\app\\src\\test\\java\\org\\cs305\\testFile.txt");
            Writer.write(ctx.body());
            Writer.close();

//            xmlParser parMsg = new xmlParser(ctx.body().trim());
//            var values = new HashMap<String, String>() {{
//                put("Sender", parMsg.Sender);
//                put("MessageType", "Acknowledgement");
//                put("MessageUUID", parMsg.MessageUUID);
//            }};
//
//            var objectMapper = new ObjectMapper();
//            String requestBody = objectMapper
//                    .writeValueAsString(values);
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(parMsg.Sender))
//                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                    .build();
//            HttpResponse<String> response = client.send(request,
//                    HttpResponse.BodyHandlers.ofString());
//        });
        });
    }
}
