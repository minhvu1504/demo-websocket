package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by minhvu on 24/08/2016.
 */
public class Main {

  public static void main(String args[]) {
    final Server server = new Server();
    ServerConnector connector = new ServerConnector(server);
    connector.setPort(8080);
    server.addConnector(connector);

    // Setup the basic application "context" for this application at "/"
    // This is also known as the handler tree (in jetty speak)
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);

    // Add a websocket to a specific path spec
    ServletHolder holderEvents = new ServletHolder("ws-events", EventServlet.class);
    context.addServlet(holderEvents, "/events/*");

    // ==================
    Thread thread = new Thread(new Runnable() {
      public void run() {
        while (true) {
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          if (SessionManager.sessionMap.size() > 0) {
            Iterator<String> it = SessionManager.sessionMap.keySet().iterator();
            while (it.hasNext()) {
              String key = it.next();
              Session session = SessionManager.sessionMap.get(key);
              try {
                session.getRemote().sendString("hello : " + key);
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          } else {
            System.out.println("sessionMap is empty");
          }
        }
      }
    });

    try {
      server.start();
      server.dump(System.err);
      server.join();
    } catch (Throwable t) {
      t.printStackTrace(System.err);
    }
  }
}
