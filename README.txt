1. What does this Program do
This program implements a message routing service, where clients can communicate with each others via a message routing service.

2. A description of how this program works
The program starts by parsing the configuration file provided to the Message Router. This makes the connection to the sqlite databse. Then the program starts the message Router server, where it listens via HTTP POST.
If it receives a message, xmlParser is used to parse the msg, and lookRoutingTable determines the destination of the message by accessing the routing table.
It makes the corresponding RECEIVED entry in message_logs table.
It then forwards the message to the required destination.
It makes the corresponding SENT entry in the message_logs table.

3. How to run the program
Create two tables in a database:
create table message_logs (PK INTEGER primary key, RouteId int, EventType VARCHAR(100), EventTime VARCHAR(100));
create table routing_table (RouteId int, Sender VARCHAR(100), MessageType VARCHAR(100), Destination VARCHAR(100));

Then add entries in the table
insert into routing_table values(1, 'http://127.0.0.1:23001/foo', 'VALIDATE_PAN', 'http://127.0.0.1:44001/check_pan');
insert into routing_table values(2, 'http://localhost:3030/', 'VALIDATE_PAN', 'http://localhost:3030/');

Then run the test of the project:
By clicking on run msgRouterTest.java button in IntelliJ
or run gradle test

4. Sample Execution

> Task :app:compileJava UP-TO-DATE
> Task :app:processResources NO-SOURCE
> Task :app:classes UP-TO-DATE
> Task :app:compileTestJava UP-TO-DATE
> Task :app:processTestResources NO-SOURCE
> Task :app:testClasses UP-TO-DATE
> Task :app:test
---- IntelliJ IDEA coverage runner ---- 
sampling ...
include patterns:
exclude patterns:
127.0.0.1
jdbc:sqlite:/D:\Course_materials\CS305\midsem\app\src\main\java\org\cs305\messages_db.db
D:\Course_materials\CS305\midsem\app\src\main\java\org\cs305\logFile.txt
77001
[Test worker] INFO io.javalin.Javalin - 
       __                      __ _            __ __
      / /____ _ _   __ ____ _ / /(_)____      / // /
 __  / // __ `/| | / // __ `// // // __ \    / // /_
/ /_/ // /_/ / | |/ // /_/ // // // / / /   /__  __/
\____/ \__,_/  |___/ \__,_//_//_//_/ /_/      /_/

          https://javalin.io/documentation

[Test worker] INFO org.eclipse.jetty.util.log - Logging initialized @17430ms to org.eclipse.jetty.util.log.Slf4jLog
[Test worker] INFO io.javalin.Javalin - Starting Javalin ...
[Test worker] INFO io.javalin.Javalin - You are running Javalin 4.3.0 (released January 13, 2022).
[Test worker] INFO io.javalin.Javalin - Listening on http://localhost:7070/
[Test worker] INFO io.javalin.Javalin - Javalin started in 1845ms \o/
[Test worker] INFO io.javalin.Javalin - 
       __                      __ _            __ __
      / /____ _ _   __ ____ _ / /(_)____      / // /
 __  / // __ `/| | / // __ `// // // __ \    / // /_
/ /_/ // /_/ / | |/ // /_/ // // // / / /   /__  __/
\____/ \__,_/  |___/ \__,_//_//_//_/ /_/      /_/

          https://javalin.io/documentation

[Test worker] INFO io.javalin.Javalin - Starting Javalin ...
[Test worker] INFO io.javalin.Javalin - You are running Javalin 4.3.0 (released January 13, 2022).
[Test worker] INFO io.javalin.Javalin - Listening on http://localhost:3030/
[Test worker] INFO io.javalin.Javalin - Javalin started in 32ms \o/
http://localhost:3030/
VALIDATE_PAN
573fbfa0-97e7-11ec-8fbc-bf1589110003
GOOG,INFY,AAPL
<?xml version="1.0" encoding="UTF-8" ?>
<Message>
    <!-- It is the serviceâ€™s endpoint URL. -->
    <Sender>http://localhost:3030/</Sender>
    <!-- A string that defines a message type. -->
    <MessageType>VALIDATE_PAN</MessageType>
    <!-- A  universally unique ID for a message. -->
    <MessageUUID>573fbfa0-97e7-11ec-8fbc-bf1589110003</MessageUUID>
    <!-- This is the main payload of the message.
    Its content will always be a CDATA section. -->
    <Body>
        <![CDATA[
       GOOG,INFY,AAPL
       ]]>
    </Body>
</Message>

Class transformation time: 4.5519712s for 4201 classes or 0.0010835446798381338s per class
BUILD SUCCESSFUL in 25s
3 actionable tasks: 1 executed, 2 up-to-date
21:06:37: Execution finished ':app:test --tests "org.cs305.msgRouterTest"'.





