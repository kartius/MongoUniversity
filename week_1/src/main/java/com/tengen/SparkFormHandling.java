package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SparkFormHandling {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");
        Spark.get("/", new Route() {
            public Object handle(final Request request, final Response response) {
                StringWriter stringWriter = new StringWriter();
                try {
                    Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
                    Map<String, Object> fruitMap = new HashMap();
                    fruitMap.put("fruits", Arrays.asList("apple", "orange", "banana", "paeach"));
                    fruitPickerTemplate.process(fruitMap, stringWriter);
                    System.out.println(stringWriter);
                    return stringWriter;

                } catch (Exception e) {
                    Spark.halt(500);
                    e.printStackTrace();
                }
                return null;
            }
        });
        Spark.post("/favorite_fruit", new Route() {
            public Object handle(final Request request, final Response response) {
                final String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "Please, pick fruit";
                } else {
                    return "You favorite fruite is " + fruit;
                }
            }
        });
    }
}
