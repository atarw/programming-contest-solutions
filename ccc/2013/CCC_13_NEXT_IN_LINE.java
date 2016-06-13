import java.io.*;

public class CCC_13_NEXT_IN_LINE {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int A = Integer.parseInt (in.readLine ()), B = Integer.parseInt (in.readLine ());
    
    System.out.println (B * 2 - A);
  }
}