package com.iamplus.news;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.get("/testJson").handler(new TestJsonHandler());

        server.requestHandler(router::accept)
                .listen(getHerokuAssignedPort());


        System.out.println("HTTP server started on port 8080");
    }

    private static int getHerokuAssignedPort() {

        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
