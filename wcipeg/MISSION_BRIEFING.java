import java.io.*;
import java.util.*;

public class MISSION_BRIEFING {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    HashSet <Integer> set = new HashSet <Integer> ();
    String ln = in.readLine ();
    
    int index = 0;
    
    for (int i = 1; i <= 9; i++) {
      index = ln.indexOf ("00" + i);
      
      if (index != -1) {
        set.add (i);
      }
    }
    
    System.out.println (set.size ());
  }
}