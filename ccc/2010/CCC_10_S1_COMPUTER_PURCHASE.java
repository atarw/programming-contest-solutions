import java.io.*;
import java.util.*;

public class CCC_10_S1_COMPUTER_PURCHASE {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    
    int best1 = -1, best2 = -1;
    String name1 = "", name2 = "";
    
    for (int i = 0; i < N; i++) {
      String [] t = in.readLine ().split (" ");
      int R = Integer.parseInt (t [1]), S = Integer.parseInt (t [2]), D = Integer.parseInt (t [3]);
      int val = 2 * R + 3 *S + D;
      
      if (val >= best1) {
        if (val == best1 && t [0].compareTo (name1) < 0 || val > best1) {
          best2 = best1;
          name2 = name1;
          
          best1 = val;
          name1 = t [0];
        }
      }
      
      if (val >= best2 && (val < best1 || t [0].compareTo (name1) > 0)  || t [0].compareTo (name1) > 0 && val == best1) {
        if (val == best2 && t [0].compareTo (name2) < 0 || val > best2) {
          best2 = val;
          name2 = t [0];
        }
      }
    }
    
    String [] names = {name1, name2};
    
    if (best1 == best2) {
      Arrays.sort (names);
    }
    
    System.out.println (names [0] + "\n" + names [1]);
  }
}