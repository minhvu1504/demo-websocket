package server;

/**
 * Created by minhvu on 24/08/2016.
 */
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@SuppressWarnings("serial")
public class EventServlet extends WebSocketServlet {
  @Override
  public void configure(WebSocketServletFactory factory) {
    factory.register(EventSocket.class);
  }
}
