package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldFreeMakerStyle {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMakerStyle.class, "/");
        try {
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> helloMap = new HashMap();
            helloMap.put("name","Freemaker");
            helloTemplate.process(helloMap,stringWriter);
            System.out.println(stringWriter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
