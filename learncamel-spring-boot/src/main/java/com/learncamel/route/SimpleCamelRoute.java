package com.learncamel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by z001qgd on 1/24/18.
 */
@Component
public class SimpleCamelRoute  extends RouteBuilder{
    @Override
    public void configure() throws Exception {

        from("timer:hello?period=10s")
                .log("Timer Invoked and the body ${body}")
                .pollEnrich("file:data/input?delete=true&readLock=none")
                .to("file:data/output");


    }
}
