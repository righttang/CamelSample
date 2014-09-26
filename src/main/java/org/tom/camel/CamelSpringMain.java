package org.tom.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * Created by tangh2 on 26/09/2014.
 */


@Configuration
@ComponentScan(basePackages = {
        "org.tom.camel" })
@Import({ CamelJMSConfig.class })
@EnableAutoConfiguration
public class CamelSpringMain {
    private static final String CAMEL_URL_MAPPING = "/camel/*";
    private static final String CAMEL_SERVLET_NAME = "CamelServlet";

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) throws Exception{
        ApplicationContext ctx = SpringApplication.run(CamelSpringMain.class, args);
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(CamelSpringMain.class);
        System.out.println(ctx.getBean("activeMq").getClass());
    }

    @Bean(name = "camelContext")
    public CamelContext camel() throws Exception {
        SpringCamelContext camelContext = SpringCamelContext.springCamelContext(applicationContext, false);

        //Register with Camel context all routes found in the Spring application context.
        //We need to loop since as of Camel version 2.4, automatic scanning is only possible to be specified in the xml config.
        Map<String, SpringRouteBuilder> beansOfType = applicationContext.getBeansOfType(SpringRouteBuilder.class);
        for (RouteBuilder routeBuilder : beansOfType.values()) {
            camelContext.addRoutes(routeBuilder);
            //camelContext.addRoutes(new CopyFileRoute());
        }

        camelContext.setTracing(true);
        camelContext.start();
        return camelContext;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }
}
