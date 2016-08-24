package server;

import org.cliffc.high_scale_lib.NonBlockingHashMap;
import org.eclipse.jetty.websocket.api.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by minhvu on 24/08/2016.
 */
public class SessionManager {
  public static Map<String, Session> sessionMap = new NonBlockingHashMap<String, Session>();
}
