import java.io.*;

public class Infinity {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    
    long A = Long.parseLong (in.readLine ().toUpperCase (), 16);
    long B = Long.parseLong (in.readLine ().toUpperCase (), 16);
    long INF = Long.parseLong ("3F3F3F3F", 16);
    
    System.out.println (A + B > INF ? "Yes" : "No");
  }
}