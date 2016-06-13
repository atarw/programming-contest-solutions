import java.io.*;
import java.util.*;

public class CCC_06_CIPHER_TEXT {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    Map <Character, Character> map = new HashMap <Character, Character> ();
    
    String plain = in.readLine ();
    String cip1 = in.readLine ();
    String cip2 = in.readLine ();
    
    for (int i = 0; i < plain.length (); i++) {
      map.put (cip1.charAt (i), plain.charAt (i));
    }
    
    for (int i = 0; i < cip2.length (); i++) {
      if (map.containsKey (cip2.charAt (i))) {
        System.out.print (map.get (cip2.charAt (i)));
      }
      else {
        System.out.print (".");
      }
    }
  }
}