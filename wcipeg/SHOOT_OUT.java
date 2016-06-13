import java.io.*;
import java.util.*;

public class SHOOT_OUT {
  
  static int [] ppl, doors;
  
  public static int binary (int H) {
    int low = 0, mid = ppl.length / 2, high = ppl.length - 1;
    
    while (low <= high) {
      if (ppl [mid] < H) {
        low = mid + 1;
      }
      else if (ppl [mid] > H) {
        high = mid - 1;
      }
      
      mid = (low + high) / 2;
    }
    
    return mid;
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    ppl = new int [N];
    doors = new int [M];
    NavigableSet <Integer> set = new TreeSet <Integer> ();
    
    for (int n = 0; n < N; n++) {
      ppl [n] = Integer.parseInt (in.readLine ());
    }
    
    for (int m = 0; m < M; m++) {
      doors [m] = Integer.parseInt (in.readLine ());
      set.add (doors [m]);
    }
    
    Arrays.sort (ppl);
    int val = 0;
    
    for (int m = 0; m < M - 1; m++) {      
      set.remove (doors [m]);
      val = binary (set.first ());
      System.out.println (val == 0 ? val : (val + 1));
    }
    System.out.println (N);
  }
}