import java.io.*;
import java.util.*;

public class DMPG_16_S3_THE_FASTER_WAY {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), R = Integer.parseInt (t [1]), M = Integer.parseInt (t [2]);
    
    t = in.readLine ().split (" ");
    
    int [] rocket = new int [R];
    
    for (int r = 0; r < R; ++r) {
      rocket [r] = Integer.parseInt (t [r]);
    }
    
    Arrays.sort (rocket);
    
    t = in.readLine ().split (" ");
    int s = 0, r_ppl = 0, n_ppl = 0;
    
    for (int m = 0; m < M; ++m) {
      s = Integer.parseInt (t [m]);
      
      if (Arrays.binarySearch (rocket, s) >= 0) {
        ++r_ppl;
      }
      else {
        ++n_ppl;
      }
    }
    
    if (r_ppl >= n_ppl) {
      n_ppl = M / 2; r_ppl = (M + 1) / 2;
    }
    
    long r_time = (r_ppl * (r_ppl + 1)) / 2, n_time = (n_ppl * (n_ppl + 1)) / 2;
    
    System.out.print (r_time + n_time);
  }
}