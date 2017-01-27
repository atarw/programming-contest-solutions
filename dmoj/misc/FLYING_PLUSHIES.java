import java.io.*;

public class FLYING_PLUSHIES {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    int M = Integer.parseInt (in.readLine ());
    int C = 0;
    
    for (int i = 0; i < M; i++) {
      if (Integer.parseInt (in.readLine ()) >= N) {
        C++;
      }
    }
    
    System.out.println (C);
  }
}