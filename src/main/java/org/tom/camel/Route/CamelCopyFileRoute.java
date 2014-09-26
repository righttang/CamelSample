package org.tom.camel.Route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by tangh2 on 25/09/2014.
 */

@Component
public class CamelCopyFileRoute extends SpringRouteBuilder {
    @Override public void configure() throws Exception {
        from("file:Inbox?delete=true").to("file:Outbox");
    }
}
