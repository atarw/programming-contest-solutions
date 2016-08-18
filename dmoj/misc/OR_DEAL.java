import java.io.*;

public class OR_DEAL {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    long N = Long.parseLong (in.readLine ());
   // long ans = 1;
    long length = Long.toBinaryString (N).length ();
    
   /* for (long i = 2; i <= N; i = i + 1L) {
      ans |= i;
    }*/
    
    for (int i = 0; i < length; i++) {
      System.out.print ("1");
    }
  }
}