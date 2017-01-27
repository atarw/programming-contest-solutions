import java.io.*;
import java.util.*;

public class CCC_14_S3_GENEVA_CONFECTION {
  
  public static boolean possible (Deque <Integer> deque) {
    Deque <Integer> branch = new ArrayDeque <Integer> ();
    int last = 0;
    
    while (!deque.isEmpty () || !branch.isEmpty ()) {
      if (!deque.isEmpty () && last + 1 == deque.peek ()) {
        last = deque.poll ();
      }
      else if (!branch.isEmpty () && last + 1 == branch.peek ()) {
        last = branch.poll ();
      }
      else if (!deque.isEmpty ()) {
        branch.push (deque.poll ());
      }
      else {
        break;
      }
    }
    
    return branch.isEmpty () && deque.isEmpty ();
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    Deque <Integer> original = new ArrayDeque <Integer> ();
    int T = Integer.parseInt (in.readLine ());
    
    for (int t = 0; t < T; t++) {
      int N = Integer.parseInt (in.readLine ());
      
      for (int n = 0; n < N; n++) {
        original.push (Integer.parseInt (in.readLine ()));
      }
      
      System.out.println (possible (original) ? "Y" : "N");
      
      original.clear ();
    } 
  }
}