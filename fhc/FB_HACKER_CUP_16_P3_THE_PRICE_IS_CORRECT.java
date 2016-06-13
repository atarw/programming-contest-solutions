import java.io.*;
import java.util.*;

public class FB_HACKER_CUP_16_P3_THE_PRICE_IS_CORRECT {
  
  static NavigableSet <Long> set = new TreeSet <Long> ();
  
  public static long sets (int P, int [] B) {
    long count = 0;
    long sum = 0;
    
    for (int b = 0; b < B.length; b++) {
      sum = sum + B [b];
      
      count += set.tailSet (sum - P, true).size ();
      set.add (sum);
    }
    
    return count;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new FileReader (new File ("the_price_is_correct.txt")));
    PrintWriter out = new PrintWriter (new FileWriter (new File ("the_price_is_correct_answers.txt")));
    
    int T = Integer.parseInt (in.readLine ());
    String [] tokens;
    int N, P;
    
    int [] B;
    
    for (int t = 1; t <= T; t++) {
      tokens = in.readLine ().split (" ");
      N = Integer.parseInt (tokens [0]);
      P = Integer.parseInt (tokens [1]);
      
      tokens = in.readLine ().split (" ");
      
      B = new int [N];
      
      for (int i = 0; i < N; i++) {
        B [i] = Integer.parseInt (tokens [i]);
      }
      
      System.out.println (t);
      //System.out.println ("Case #" + t + ": " + sets (P, B));
      out.println ("Case #" + t + ": " + sets (P, B));
      
      set.clear ();
      set.add (0L);
    }
    in.close ();
    out.close ();
  }
}