import java.io.*;

public class DMPG_16_S5_PIZZA_BAG {
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), K = Integer.parseInt (t [1]);
    int [] arr = new int [2 * N];//handle loop around
    
    t = in.readLine ().split (" ");
    
    for (int n = 0; n < N; n++) {
      arr [n] = Integer.parseInt (t [n]);
      arr [N + n] = arr [n];
    }
    
    long max = 0, curr = 0;
    int k = 0;
    
    for (int n = 0; n < arr.length; n++) {
      curr = curr + arr [n];
      k++;
      
      if (max < curr) {
        max = curr;
      }
      if (curr < 0 || k == K) {
        curr = 0;
        k = 0;
      }
    }
    
    System.out.print (max);
  }
}