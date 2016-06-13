import java.io.*;
import java.util.*;

public class FriendshipNumber {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    NavigableMap <Integer, Integer> map = new TreeMap <Integer, Integer> ();
    int N = Integer.parseInt (in.readLine ());
    
    for (int i = 0; i < N; i++) {
      String [] t = in.readLine ().split (" ");
      int p = 1;
      
      for (int j = 1; j < t.length; j++) {
        p *= Integer.parseInt (t [j]);
      }
      map.put (p, i + 1);
    }
    
    for (int i = 0; i < 3; i++) {
      System.out.println (map.get (map.lastKey ()));
      map.remove (map.lastKey ());
    }
  }
}