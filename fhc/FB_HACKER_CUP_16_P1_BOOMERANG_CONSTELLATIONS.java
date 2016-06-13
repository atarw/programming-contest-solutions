import java.io.*;
import java.util.*;
import java.awt.Point;

public class FB_HACKER_CUP_16_P1_BOOMERANG_CONSTELLATIONS {
  
  static Map <Double, Integer> map = new HashMap <Double, Integer> ();
  
  public static int found (Point [] arr, int i) {
    int found = 0;
    double dist = 0;
    
      for (int y = i; y < arr.length; y++) {
        dist = arr [i - 1].distance (arr [y]);
        
        if (!map.containsKey (dist)) {
          map.put (dist, 1);
        }
        else {
          found += map.get (dist);
          map.put (dist, map.get (dist) + 1);
        }
        
        if (!map.containsKey (dist)) {
          map.put (dist, 1);
        }
        else {
          found += map.get (dist);
          map.put (dist, map.get (dist) + 1);
        }
    }
    
    return found;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    //BufferedReader in = new BufferedReader (new FileReader (new File ("boomerang_constellations.txt")));
    //PrintWriter out = new PrintWriter (new FileWriter (new File ("boomerang_constellations_atharva.txt")));
    
    int T = Integer.parseInt (in.readLine ());
    int N;
    String [] tokens;
    Point [] arr;
    
    for (int t = 1; t <= T; t++) {
      N = Integer.parseInt (in.readLine ());
      arr = new Point [N];
      
      for (int n = 0; n < N; n++) {
        tokens = in.readLine ().split (" ");
        arr [n] = new Point (Integer.parseInt (tokens [0]), Integer.parseInt (tokens [1]));
      }
      
      int f = 0;
      
      for (int i = 0; i < arr.length; i++) {
        map = new HashMap <Double, Integer> ();
        
        f = found (arr, i + 1);
      }
      
      //out.println ("Case #" + t + ": " + f);
      //System.out.println (t);
      System.out.println ("Case #" + t + ": " + f);
    }
    
    //in.close ();
    //out.close ();
  }
}