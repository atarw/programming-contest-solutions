import java.io.*;

public class CCC_04_TERMS_OF_OFFICE {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ()), M = Integer.parseInt (in.readLine ());
    
    for (int x = N; x <= M; x+=60) {
      System.out.println ("All positions change in year " + x);
    }
  }
}