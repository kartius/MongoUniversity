package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldSparkFreemakerStyle {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemakerStyle.class, "/");
        Spark.get("/", new Route() {
            public Object handle(final Request request, final Response response) {
                StringWriter stringWriter = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    Map<String, Object> helloMap = new HashMap();
                    helloMap.put("name", "Freemaker");
                    helloTemplate.process(helloMap, stringWriter);
                    System.out.println(stringWriter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return stringWriter;
            }
        });
    }
}
