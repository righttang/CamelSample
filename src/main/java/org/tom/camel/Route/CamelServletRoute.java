package org.tom.camel.Route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelServletRoute extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        // Access us using http://localhost:8080/camel/hello
        from("servlet:///hello").transform().constant("Hello from Camel!");

        // Trigger run right after startup. No Servlet request required.
        //from("timer://foo?fixedRate=true&period=10s").log("Camel timer triggered.");
    }
}
