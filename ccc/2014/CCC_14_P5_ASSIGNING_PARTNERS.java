import java.io.*;
import java.util.*;

public class CCC_14_P5_ASSIGNING_PARTNERS {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    Map <String, String> map = new HashMap <String, String> ();
    
    int N = Integer.parseInt (in.readLine ());
    String [] n1 = in.readLine ().split (" "), n2 = in.readLine ().split (" ");
    
    boolean good = true;
    
    for (int i = 0; i < N; i++) {
      map.put (n1 [i], n2 [i]);
    }
    
    for (int i = 0; i < N; i++) {
      if (n1 [i].equals (n2 [i]) || !map.get (n2 [i]).equals (n1 [i])) {
        good = false;
        break;
      }
    }
    
    System.out.println (good ? "good" : "bad");
  }
}