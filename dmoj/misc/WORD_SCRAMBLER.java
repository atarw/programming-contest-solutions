import java.io.*;
import java.util.*;

public class WORD_SCRAMBLER {
  
  static NavigableSet <String> set;
  
  public static void permute (String p, String ln) {
    
    if (ln.isEmpty ()) {
      set.add (p);
    }
    else {
      for (int i = 0; i < ln.length (); i++) {
        permute (p + ln.charAt (i), ln.substring (0, i) + ln.substring (i + 1));
      }
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    set = new TreeSet <String> ();
    
    permute ("", in.readLine ());
    
    for (String i : set) {
      System.out.println (i);
    }
  }
}