import java.io.*;
import java.util.*;

public class JDCC_15_FEBUARY_E_POCKET_CHANGE {
  
  static int [] grocer, rosie, cache2;
  static int [][] cache;
  
  public static int change (int amt) {
    if (cache2 [amt] == 0 && amt != 0) {
      cache2 [amt] = Integer.MAX_VALUE;
      
      for (int g : grocer) {
        if (amt >= g) {
          cache2 [amt] = Math.min (cache2 [amt], 1 + change (amt - g));
        }
        else {
          break;
        }
      }
    }
    
    return cache2 [amt];
  }
  
  public static int combo (int N, int amt, int K) {
    if (cache [cache.length - 1][cache [0].length - 1] == 0 && N <= rosie.length && cache [N][amt] == 0) {
      if (amt >= K) {
        cache [N][amt] = rosie.length - N + change (amt - K);
      }
      
      if (cache [N][amt] == 0 && amt != K) {
        cache [N][amt] = Integer.MAX_VALUE;
      }
      
      if (N == rosie.length - 1) {
        cache [N][amt] = Math.min (cache [N][amt], rosie.length - N + combo (N + 1, amt + rosie [N], K));
      }
      else if (N < rosie.length) {
        cache [N][amt] = Math.min (cache [N][amt], rosie.length - N + Math.min (combo (N + 1, amt, K), combo (N + 1, amt + rosie [N], K)));
      }
    }
    
    return cache [N][amt];
  }
  
  public static void main (String [] args) throws IOException {
    //BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    BufferedReader in = new BufferedReader (new FileReader (new File ("E.in")));
    PrintWriter out = new PrintWriter (new FileWriter (new File ("E2.out")));
    int T = Integer.parseInt (in.readLine ()), N, M, K, S;
    String [] t;
    
    for (int i = 0; i < T; i++) {
      t = in.readLine ().split (" ");
      N = Integer.parseInt (t [0]);
      M = Integer.parseInt (t [1]);
      K = Integer.parseInt (t [2]);
      S = 0;
      
      grocer = new int [N];
      rosie = new int [M];
      
      t = in.readLine ().split (" ");
      
      for (int n = 0; n < N; n++) {
        grocer [n] = Integer.parseInt (t [n]);
      }
      
      t = in.readLine ().split (" ");
      
      for (int m = 0; m < M; m++) {
        rosie [m] = Integer.parseInt (t [m]);
        S += rosie [m];
      }
      
      cache2 = new int [S + K + 1];
      cache = new int [M + 1][S + 1];
      
      Arrays.sort (grocer);
      Arrays.sort (rosie);
      
      combo (0, 0, K);
      
      out.println (cache [M][S]);
    }
    
    in.close ();
    out.close ();
  }
}