package org.tom.camel.Route;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRESTRoute extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/say")
                .get("/hello").to("direct:hello");
        rest("/say")
                .get("/bye").to("direct:bye");


//        from("direct:hello")
//                .transform().constant("Hello World");
//        from("direct:bye")
//                .transform().constant("Bye World");
//        from("direct:postbye")
//                .transform().constant("Post Bye World");

        from("direct:hello").to("direct:printTen");

        from("direct:bye").to("direct:printTen");

        from("direct:postbye")
                .transform().constant("Post Bye World");

        from("direct:printTen").beanRef("singletonTest","userTest");
    }
}
