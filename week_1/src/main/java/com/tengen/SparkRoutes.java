package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkRoutes {

    public static void main(String[] args) {
        Spark.get("/", new Route() {
            public Object handle(final Request request, final Response response){
                return "Hello Worldk";
            }
        });

        Spark.get("/test", new Route() {
            public Object handle(final Request request, final Response response){
                return "Test page";
            }
        });

        Spark.get("/echo/:thing", new Route() {
            public Object handle(final Request request, final Response response){
                return request.params(":thing");
            }
        });
    }
}
