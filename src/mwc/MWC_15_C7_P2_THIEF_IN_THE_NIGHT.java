import java.io.*;

public class MWC_15_C7_P2_THIEF_IN_THE_NIGHT {
  
  static int [][] arr;
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int F = Integer.parseInt (t [0]), R = Integer.parseInt (t [1]);
    arr = new int [F][R];// +1
    
    for (int f = 0; f < F; f++) {
      t = in.readLine ().split (" ");
      
      for (int r = 1; r <= R; r++) {
        //arr [f][r] = arr [f][r - 1] + Integer.parseInt (t [r - 1]);
        arr [f][r - 1] = Integer.parseInt (t [r - 1]);
      }
    }
    
    int Q = Integer.parseInt (in.readLine ());
    int a, b, c;
    
    for (int q = 0; q < Q; q++) {
      t = in.readLine ().split (" ");
      a = Integer.parseInt (t [0]) - 1; b = Integer.parseInt (t [1]); c = Integer.parseInt (t [2]) - 1;
      int sum = 0;
      
      for (int i = a; i < b; i++) {
        sum += arr [c][i];
      }
      System.out.println (sum);
      //System.out.println (arr [c][b] - arr [c][a]);
    }
  }
}