package com.learncamel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by z001qgd on 1/24/18.
 */
@Component
public class SimpleCamelRoute  extends RouteBuilder{

    @Autowired
    Environment environment;

    @Override
    public void configure() throws Exception {

        from("{{startRoute}}")
                .log("Timer Invoked and the body" + environment.getProperty("message"))
                .choice()
                    .when((header("env").isNotEqualTo("mock")))
                        .pollEnrich("{{fromRoute}}")
                    .otherwise()
                        .log("mock env flow and the body is ${body}")
                .to("{{toRoute1}}");


    }
}
