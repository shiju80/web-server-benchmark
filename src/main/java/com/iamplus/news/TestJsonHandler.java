package com.iamplus.news;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamplus.news.model.TestJson;
import spark.Request;
import spark.Response;
import spark.Route;

public class TestJsonHandler implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {

        TestJson model = new TestJson();
        model.setFramework("Spark-Jetty");
        model.setValue("Hello");

        Thread.sleep(Contants.DELAY);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(model);
    }
}
