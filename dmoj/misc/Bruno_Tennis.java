import java.io.*;
import java.util.*;

public class Bruno_Tennis {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    Deque <String> stack = new ArrayDeque <String> ();
    String result = "Not enough information";
    
    for (int i = 0; i < N / 2; i++) {
      stack.add (in.readLine ());
      stack.add (in.readLine ());
      
      if (stack.getFirst ().equals ("lob") || stack.getLast ().equals ("lob")) {
        if (stack.getFirst ().equals (stack.getLast ())) {
          result = "Possible Bruno";
        }
        else {
          result = "BruNO";
          break;
        }
      }
      stack.clear ();
    }
    System.out.println (result);
  }
}