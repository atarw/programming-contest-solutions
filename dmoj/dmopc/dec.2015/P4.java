import java.io.*;
import java.util.*;

public class P4 {
  
  static NavigableMap <Long, Long> map = new TreeMap <Long, Long> ();//stores hours to number of cities infected by that rate
  
  public static long hours (P p1, P p2) {
    int xDiff = p1.X - p2.X, yDiff = p1.Y - p2.Y;
    return xDiff * xDiff + yDiff * yDiff;
  }
  
  public static void fill (P [] arr, int X) {
    NavigableSet <Long> head = new TreeSet <Long> ();
    long cities = 0L;
    
    for (int i = 0; i < arr.length; i++) {
      long hrs = hours (arr [X], arr [i]);
      
      if (map.containsKey (hrs)) {
        cities = map.get (hrs);
      }
      
      map.put (hrs, cities + 1L);
      cities = 0L;
    }
    
    head = map.navigableKeySet ();
    
    for (Long i : head) {//updates values to be the sum of this and everything before (updated number of cities infected)
      Long key = map.lowerKey (i);
      
      if (key != null) {
        map.put (i, map.get (key) + map.get (i));
      }
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    P [] arr = new P [N];
    String [] t;
    
    for (int i = 0; i < N; i++) {
      t = in.readLine ().split (" ");
      arr [i] = new P (Integer.parseInt (t [0]), Integer.parseInt (t [1]));
    }
    
    int X = Integer.parseInt (in.readLine ()) - 1;
    int Q = Integer.parseInt (in.readLine ());
    
    fill (arr, X);
    
    System.out.println (map);
    
    for (int i = 0; i < Q; i++) {
      long H = Long.parseLong (in.readLine ());
      long total = map.floorEntry (H).getValue ();
      
      System.out.println (total);
    }
  }
}

class P {
  int X, Y;
  
  public int hashCode () {
    return X * 17 + Y * 37;
  }
  
  public boolean equals (Object o) {
    P a = (P) (o);
    
    return X == a.X && Y == a.Y;
  }
  
  public P (int X, int Y) {
    this.X = X;
    this.Y = Y;
  }
}