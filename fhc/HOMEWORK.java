import java.io.*;
import java.util.*;

public class HOMEWORK {
  
  static int [] prefix = new int [10000001];//prefix sum array of primalities
  static int [] arr = new int [10000001];//storing actual primalities
  
  public static void pre () {
    for (int i = 2; i < arr.length; i++) {
      prefix [i] = prefix [i - 1];
      
      if (arr [i] == 0) {
        for (int x = 1, y = i * x; y < arr.length; x++, y = i * x) {
          arr [y]++;
        }
        prefix [i]++;
      }
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t;
    int T = Integer.parseInt (in.readLine ());
    
    pre ();
    
    for (int i = 1; i <= T; i++) {
      t = in.readLine ().split (" ");
      int A = Integer.parseInt (t [0]), B = Integer.parseInt (t [1]), K = Integer.parseInt (t [2]);
      
      System.out.print ("Case #" + i + ": ");
      
      int amt = 0;
      
      for (int a = A; a <= B; a++) {
        if (arr [a] == K) {
          amt++;
        }
      }
      
      System.out.println (amt);
    }
  }
}