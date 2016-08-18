import java.io.*;
import java.util.*;

public class UNIQUE_ELEMENTS {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    Set <Integer> set = new HashSet <Integer> ();
    int N = Integer.parseInt (in.readLine ());
    
    for (int i = 0; i < N; i++) {
      set.add (Integer.parseInt (in.readLine ()));
    }
    
    System.out.println (set.size ());
  }
}