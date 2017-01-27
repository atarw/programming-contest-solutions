import java.io.*;
import java.util.*;

public class DMOPC_14_P4_SKETCHLEY_PARK {
  
  static String msg, word = "HAILHYDRA", cipher;
  static char [] alpha = new char [26];
  static Set <String> list = new HashSet <String> ();
  
  public static String form () {
    StringBuilder b = new StringBuilder (9);
    
    for (int i = 0; i < 9; i++) {
      b.append (cipher.charAt (word.charAt (i) - 65));
    }
    
    return b.toString ();
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    msg = in.readLine ();
    
    int N = Integer.parseInt (in.readLine ());
    
    for (int i = 0; i + 8 < msg.length (); i++) {
      if (msg.charAt (i) == msg.charAt (i + 4) && msg.charAt (i + 1) == msg.charAt (i + 8) && msg.charAt (i + 2) != msg.charAt (i + 3) && msg.charAt (i + 3) != msg.charAt (i + 5) && msg.charAt (i + 5) != msg.charAt (i + 6) && msg.charAt (i + 6) != msg.charAt (i + 7)) {
        list.add (msg.substring (i, i + 9));
        i += 9;
      }
    }
    
    if (!list.isEmpty ()) {
      for (int n = 0; n < N; n++) {
        cipher = in.readLine ();
        
        if (list.contains (form ())) {
          for (int a = 0; a < 26; a++) {
            alpha [cipher.charAt (a) - 65] = (char) (a + 65);
          }
          
          for (int j = 0; j < msg.length (); j++) {
            System.out.print (alpha [msg.charAt (j) - 65]);
          }
          
          return;
        }
      }
    }
    
    System.out.println ("KeyNotFoundError");
  }
}