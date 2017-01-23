import java.io.*;

public class CCC_09_J1_ISBN {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int V = 91;
    int A = Integer.parseInt (in.readLine ());
    int B = Integer.parseInt (in.readLine ());
    int C = Integer.parseInt (in.readLine ());
    
    System.out.println ("The 1-3-sum is " + (V + A + C + B * 3));
  }
}