package com.iamplus.news;

import com.iamplus.news.model.TestJson;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class TestJsonHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {

        TestJson model = new TestJson();
        model.setFramework("Vertx");
        model.setValue("Hello");

        routingContext.vertx().setTimer(Constants.DELAY, timerID -> routingContext.response().end(Json.encode(model)));
    }
}
