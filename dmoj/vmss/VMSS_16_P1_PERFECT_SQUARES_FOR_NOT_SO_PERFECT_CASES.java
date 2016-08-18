import java.io.*;

public class VMSS_16_P1_PERFECT_SQUARES_FOR_NOT_SO_PERFECT_CASES {
  
  public static int pre (int S, int E) {
    int max = (int) Math.sqrt (E) + 1;
    int min = (int) (Math.ceil (Math.sqrt (S)));
    
    return max - min;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    
    int S = Integer.parseInt (t [0]), E = Integer.parseInt (t [1]);
    
    if (S <= E && !(S < 0 && E < 0)) {
      System.out.println (pre (S, E));
    }
    else {
      System.out.println (0);
    }
  }
}