package com.iamplus.news;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        int port = getHerokuAssignedPort();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.get("/testJson").handler(new TestJsonHandler());
        router.route().handler(StaticHandler.create("public"));

        server.requestHandler(router::accept)
                .listen(port);


        System.out.println("HTTP server started on Port " + port);
    }

    private static int getHerokuAssignedPort() {

        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("DELAY") != null) {
            Constants.DELAY = Integer.parseInt(processBuilder.environment().get("DELAY"));
            System.out.println("Delay read as " + Constants.DELAY);
        }else{
            Constants.DELAY = 1000;
            System.out.println("Delay not read, set to  " + Constants.DELAY);
        }

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
