import java.io.*;

public class A_PLUS_B {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ());
    String [] tokens;
    
    for (int i = 0; i < T; i++) {
      tokens = (in.readLine ()).split (" ");
      
      System.out.println (Integer.parseInt (tokens [0]) + Integer.parseInt (tokens [1]));
    }
  }
}