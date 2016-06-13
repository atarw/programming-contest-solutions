import java.io.*;

public class BrunoNTrig {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int A = Integer.parseInt (in.readLine ());
    int B = Integer.parseInt (in.readLine ());
    int C = Integer.parseInt (in.readLine ());
    
    if (A + B <= C || A + C <= B || B + C <= A) {
      System.out.println ("Maybe I should go out to sea...");
    }
    else {
      System.out.println ("Huh? A triangle?");
    }
  }
}