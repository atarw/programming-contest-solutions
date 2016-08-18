import java.io.*;

public class VMSS_16_P1_THE_MOST_IMPORTANT_SKILL_IN_BIOLOGY {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    String [] t;
    
    int [] x = new int [N], y = new int [N];
    
    for (int n = 0; n < N; n++) {
      t = in.readLine ().split (" ");
      x [n] = Integer.parseInt (t [0]);
      y [n] = Integer.parseInt (t [1]);
    }
    
    int v1 = 0, v2 = 0;
    
    for (int n = 0; n < N - 1; n++) {
      v1 += x [n] * y [n + 1];
      v2 += y [n] * x [n + 1];
    }
    
    v1 += x [N - 1] * y [0];
    v2 += y [N - 1] * x [0];
    
    System.out.print ((int) Math.ceil (Math.abs (v1 - v2) / 2.0));
  }
}