import java.io.*;
import java.util.*;

public class P2 {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    String [] t = in.readLine ().split (" ");
    long [] arr = new long [N];
    
    for (int i = 0; i < N; i++) {
      arr [i] = Long.parseLong (t [i]);
    }
    
    long sum = 0;
    
    for (long i : arr) {
      sum += i;
    }
    
    long time = -1;
    long avr = sum / N;
    
    if (sum % N == 0) {
      time = 0;
      for (int i = 0; i < arr.length; i++) {
        if (arr [i] < avr) {
          time += (avr - arr [i]);
        }
      }
    }
    
    System.out.println (time == -1 ? "Impossible" : time);
  }
}