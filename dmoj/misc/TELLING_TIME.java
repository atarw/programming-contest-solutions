import java.io.*;

public class TELLING_TIME {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), G = Integer.parseInt (t [1]), F, C = 0;
    
    for (int n = 0; n < N; n++) {
      F = Integer.parseInt (in.readLine ());
      
      if (F % G == 0) {
        C++;
      }
    }
    
    System.out.println (C);
  }
}