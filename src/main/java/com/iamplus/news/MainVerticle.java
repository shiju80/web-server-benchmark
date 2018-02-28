package com.iamplus.news;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.get("/testJson").handler(new TestJsonHandler());
        router.route().handler(StaticHandler.create("public"));

        server.requestHandler(router::accept)
                .listen(getHerokuAssignedPort());


        System.out.println("HTTP server started on Port " +getHerokuAssignedPort());
    }

    private static int getHerokuAssignedPort() {

        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        if (processBuilder.environment().get("DELAY") != null) {
            Constants.DELAY = Integer.parseInt(processBuilder.environment().get("DELAY"));
            System.out.println("Delay " + Constants.DELAY);
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
