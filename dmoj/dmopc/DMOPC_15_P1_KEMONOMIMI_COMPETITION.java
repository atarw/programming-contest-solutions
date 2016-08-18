import java.io.*;

public class DMOPC_15_P1_KEMONOMIMI_COMPETITION {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    
    t = in.readLine ().split (" ");
    int [] C = new int [4];
    
    for (int c = 0; c < C.length; c++) {
      C [c] = Integer.parseInt (t [c]);
    }
    
    int left = 180;
    int [] I = new int [N], P = new int [N], S = new int [N], T = new int [N];
    
    for (int n = 0; n < N; n++) {
      t = in.readLine ().split (" ");
      I [n] = Integer.parseInt (t [0]) - 1;
      P [n] = Integer.parseInt (t [1]);
      S [n] = Integer.parseInt (t [2]);
      T [n] = Integer.parseInt (t [3]);
      
      left = Math.min (left, 180 - S [n]);
    }
    
    for (int n = 0; n < N; n++) {
      if (T [n] * C [I [n]] <= left || P [n] == 10) {
        System.out.println (10 - P [n]);
      }
      else {
        System.out.println ("The kemonomimi are too cute!");
      }
    }
  }
}