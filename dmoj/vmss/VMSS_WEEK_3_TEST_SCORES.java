import java.io.*;
import java.util.*;

public class VMSS_WEEK_3_TEST_SCORES {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]);
    int K = Integer.parseInt (t [1]);
    
    Integer [] arr = new Integer [N];
    
    for (int n = 0; n < N; n++) {
      arr [n] = Integer.parseInt (in.readLine ());
    }
    
    Arrays.sort (arr, Collections.reverseOrder ());
    
    int sum = 0;
    
    
    for (Integer i : arr) {
      if (K == 0) {
        break;
      }
      else if (i > 0) {
        sum += i;
        K--;
      }
    }
    
    System.out.println (sum);
  }
}