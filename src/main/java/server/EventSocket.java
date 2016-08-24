package server;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.common.WebSocketSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by minhvu on 24/08/2016.
 */
public class EventSocket extends WebSocketAdapter {

  @Override
  public void onWebSocketConnect(Session session) {
    super.onWebSocketConnect(session);
    System.out.println("--- begin connect");

    if (session.getUpgradeRequest().getParameterMap().containsKey("token")) {
      String token = session.getUpgradeRequest().getParameterMap().get("token").get(0);
      SessionManager.sessionMap.put(token, session);
    }

  }

  @Override
  public void onWebSocketText(String message) {
    super.onWebSocketText(message);
    System.out.println("--- begin receive text message: " + message);
      try {
      getSession().getRemote().sendString("hello client, your message is " + message);
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void onWebSocketClose(int statusCode, String reason) {
    super.onWebSocketClose(statusCode, reason);
    System.out.println("--- begin close connection");
  }
}
