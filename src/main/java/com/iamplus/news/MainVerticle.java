package com.iamplus.news;

import spark.Spark;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
public class MainVerticle {

    public static void main(String[] args) {

        staticFiles.location("/public");

        port(getHerokuAssignedPort());

        Spark.get("/testJson",new TestJsonHandler());

        System.out.println("HTTP server started on Port " +getHerokuAssignedPort());
    }

    private static int getHerokuAssignedPort() {

        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("DELAY") != null) {
            Contants.DELAY = Integer.parseInt(processBuilder.environment().get("DELAY"));
        }

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
