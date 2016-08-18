import java.io.*;

public class WAITING {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (":");
    String [] s = in.readLine ().split (":");
    
    System.out.println ((Integer.parseInt (s [1]) * 60 + Integer.parseInt (s [0]) * 3600 + Integer.parseInt (s [2])) - (Integer.parseInt (t [1]) * 60 + Integer.parseInt (t [0]) * 3600 + Integer.parseInt (t [2])));
  }
}