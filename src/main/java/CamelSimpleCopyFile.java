/**
 * Created by tangh2 on 25/09/2014.
 */
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelSimpleCopyFile {

    public static void main(String args[]) throws Exception{

        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new CopyFileRoute());

        context.start();
        Thread.sleep(10000);
        context.stop();
    }



}

