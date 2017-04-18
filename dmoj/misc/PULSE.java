import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PULSE {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]);
    int S = Integer.parseInt (t [1]);
    int T = Integer.parseInt (t [2]);
    
    int count = 0;
    
    for (int i = 0; i < N; i++) {
      int X = Integer.parseInt (in.readLine ()) * 2;
      
      if (X >= S && X <= T) {
        count++;
      }
    }
    
    System.out.println (count);
  }
}