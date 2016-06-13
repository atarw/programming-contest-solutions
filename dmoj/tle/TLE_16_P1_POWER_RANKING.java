import java.io.*;
import java.util.*;

public class TLE_16_P1_POWER_RANKING {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), P = Integer.parseInt (t [1]);
    
    Ppl [] arr = new Ppl [N];
    
    for (int n = 0; n < N; n++) {
      arr [n] = new Ppl (in.readLine (), 0);
    }
    
    for (int p = 0; p < P; p++) {
      t = in.readLine ().split (" ");
      
      for (int n = 0; n < N; n++) {
        arr [n].t += Integer.parseInt (t [n]);
      }
    }
    
    Arrays.sort (arr);
    
    for (int i = 0; i < arr.length; i++) {
      System.out.println ((3 + i) + ". " + arr [i].n);
    }
  }
}

class Ppl implements Comparable <Ppl> {
  String n;
  int t;
  
  public int compareTo (Ppl p) {
    return p.t - t;
  }
  
  Ppl (String n, int t) {
    this.n = n;
    this.t = t;
  }
}