import java.io.*;

public class DMOPC_14_P2_LONGEST_TUNNELS {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    long max = 0;
    
    for (int i = 0; i < N; i++) {
      String [] t = in.readLine ().split (" ");
      long diff = Long.parseLong (t [1]) - Long.parseLong (t [0]);
      
      if (max < diff) {
        max = diff;
      }
    }
    System.out.println (max);
  }
}