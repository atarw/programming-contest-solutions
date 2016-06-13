import java.io.*;

public class DMOPC_16_P3_HARVEST {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    long K = Long.parseLong (t [2]);
    int [] change = new int [N + 1];
    int [] arr = new int [N];
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      change [Integer.parseInt (t [0]) - 1]--;
      change [Integer.parseInt (t [1])]++;
    }
    
    int curr = 0;
    
    for (int i = 0; i < change.length - 1; i++) {
      curr -= change [i];
      arr [i] = M - curr;
    }
    
    int s = 0, e = 0, min = Integer.MAX_VALUE;
    long sum = 0;
    
    while (e < arr.length) {
      for (;sum < K && e < arr.length; sum += arr [e], e++) {}
      
      for (;sum >= K && s < arr.length; sum -= arr [s], s++) {
        min = Math.min (min, e - s);
      }
    }
    
    System.out.println (min == Integer.MAX_VALUE ? -1 : min);
  }
}