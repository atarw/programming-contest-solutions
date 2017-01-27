import java.io.*;
import java.util.*;

public class DMOPC_15_P2_THE_BIG_CLOCK {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int H = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    int N = Integer.parseInt (in.readLine ());
    
    M += N;
    
    if (M >= 60) {
      int r = M % 60;
      H += M / 60;
      M = r;
    }
    
    if (H >= 24) {
      H %= 24;
    }
    
    System.out.println (H + " " + M);
  }
}