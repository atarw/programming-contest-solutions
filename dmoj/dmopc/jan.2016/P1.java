import java.io.*;
import java.util.*;

public class P1 {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    
    NavigableMap <Character, List <String>> map = new TreeMap <Character, List <String>> ();
    
    String ln;
    
    for (int n = 0; n < N; n++) {
      ln = in.readLine ();
      
      if (!map.containsKey (ln.charAt (0))) {
        map.put (ln.charAt (0), new ArrayList <String> ());
      }
      
      map.get (ln.charAt (0)).add (ln);
    }
    
    for (Character i : map.keySet ()) {
      Collections.sort (map.get (i));
      
      for (int x = 0; x < map.get (i).size (); x++) {
        System.out.print (map.get (i).get (x) + (x + 1 == map.get (i).size () ? "" : ", "));
      }
      System.out.println ();
    }
  }
}