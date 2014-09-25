import org.apache.camel.builder.RouteBuilder;

/**
 * Created by tangh2 on 25/09/2014.
 */
public class CopyFileRoute extends RouteBuilder {
    @Override public void configure() throws Exception {
        from("file:Inbox?delete=true").to("file:Outbox");
    }
}
