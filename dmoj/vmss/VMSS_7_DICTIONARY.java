import java.io.*;
import java.util.*;

public class VMSS_7_DICTIONARY {
  
  static int x = 1;
  static Map <String, Integer> map = new HashMap <String, Integer> ();
  
  public static String conv (Map <String, Integer> map, String txt) {
    String [] tokens = txt.split (" ");
    StringBuilder a = new StringBuilder ();
    
    for (int i = 0; i < tokens.length; i++) {
      if (!map.containsKey (tokens [i])) {
        map.put (tokens [i], x);
        x++;
        
        a.append (tokens [i] + " ");
      }
      else {
        a.append (map.get (tokens [i]) + " ");
      }
    }
    
    return a.toString ().trim ();
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    
    for (int i = 0; i < N; i++) {
      String line = in.readLine ();
      
      while (line != null && !line.equals ("")) {
        System.out.println (conv (map, line));
        line = in.readLine ();
      }
      map.clear ();
      x = 1;
      System.out.println ();
    }
  }
}