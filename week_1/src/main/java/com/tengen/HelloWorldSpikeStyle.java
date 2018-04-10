package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSpikeStyle {

    public static void main(String[] args) {
        Spark.get("/", new Route() {
            public Object handle(final Request request, final Response response){
                return "Hello World from Spark";
            }
        });
    }
}
