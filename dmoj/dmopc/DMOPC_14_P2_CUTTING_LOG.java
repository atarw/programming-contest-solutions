import java.io.*;
import java.util.*;

public class DMOPC_14_P2_CUTTING_LOG {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    in.readLine ();
    String K = in.readLine ();
    
    List <String> t2 = new ArrayList <String> (Arrays.asList (K.split ("X")));
    
    for (int i = t2.size () - 1; i >= 0; i--) {
      if (t2.get (i).isEmpty ()) {
        t2.remove (i);
      }
    }
    
    System.out.println (t2.size ());
    
    for (int i = 0; i < t2.size (); i++) {
      System.out.println (t2.get (i));
    }
  }
}