import java.io.*;
import java.util.*;

public class CCC_14_P5_LAZY_FOX {
  
  static int [][] cache;//current point and square of distance to number of treats
  static int [][] distance;//two points to distance
  static Map <Integer, Integer> map = new HashMap <Integer, Integer> ();
  static int min = Integer.MAX_VALUE;
  
  public static int treats (int curr, int dist, boolean fromOrigin) {
    if (cache [curr][map.get (dist)] == 0 && (dist > min || fromOrigin && dist >= min)) {
      
      for (int i = 1; i < distance [0].length; i++) {
        if ((distance [curr][i] < dist || fromOrigin && distance [curr][i] <= dist) && curr != i) {
          cache [curr][map.get (dist)] = Math.max (cache [curr][map.get (dist)], 1 + treats (i, distance [curr][i], false));
        }
      }
    }
    
    return cache [curr][map.get (dist)];
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t;
    int N = Integer.parseInt (in.readLine ());
    int [] x = new int [N + 1];
    int [] y = new int [N + 1];
    distance = new int [N + 1][N + 1];
    
    for (int n = 1; n <= N; n++) {
      t = in.readLine ().split (" ");
      x [n] = Integer.parseInt (t [0]);
      y [n] = Integer.parseInt (t [1]);
    }
    
    int xsum = 0, ysum = 0, max = 0;
    
    for (int i = 0; i <= N; i++) {
      for (int n = i + 1; n <= N; n++) {
        xsum = x [i] - x [n];
        ysum = y [i] - y [n];
        
        distance [i][n] = xsum * xsum + ysum * ysum;
        distance [n][i] = distance [i][n];
        
        if (!map.containsKey (distance [i][n])) {
          map.put (distance [i][n], map.size ());
          max = Math.max (max, distance [i][n]);
          min = Math.min (min, distance [i][n]);
        }
      }
    }
    
    x = null;
    y = null;
    
    cache = new int [N + 1][map.size () + 1];
    System.out.print (treats (0, max, true));
  }
}