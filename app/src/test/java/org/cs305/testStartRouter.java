package org.cs305;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class testStartRouter {

    public static void main(String[] args) throws IOException, ParseException {
//        System.setOut(new PrintStream("/Users/al/Projects/Sarah2/std.out"));
//        System.setErr(new PrintStream("/Users/al/Projects/Sarah2/std.err"));
        msgRouter mst = new msgRouter();
        mst.startRouter();
    }

}
