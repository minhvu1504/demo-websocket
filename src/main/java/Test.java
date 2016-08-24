import java.util.HashMap;
import java.util.Map;

/**
 * Created by minhvu on 24/08/2016.
 */
public class Test {
  public static void main(String args[]) {
    Map<String, Point> mapPoint = new HashMap<String, Point>();
    Point p1 = new Point(1,2);
    mapPoint.put("p1", p1);
    p1= null;
    System.out.println(mapPoint);

  }

  public static class Point{
    public int x ;
    public int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
