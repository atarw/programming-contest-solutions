import java.io.*;

public class P3 {
  
  public static long getCuts (long N, long K) {
    long cuts = 0;
    long stacks = 1;
    
    while (stacks < N) {
      if (stacks >= K) {
        long finalCut = (N - stacks + K - 1) / K;
        stacks += finalCut;
        cuts += finalCut;
        break;
      }
      else
      {
        stacks *= 2;
        cuts++;
      }
    }
    
    return cuts;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] tokens = (in.readLine ()).split (" ");
    
    long N = Long.parseLong (tokens [0]);
    long K = Long.parseLong (tokens [1]);
    
    System.out.println (getCuts (N, K));
  }
}