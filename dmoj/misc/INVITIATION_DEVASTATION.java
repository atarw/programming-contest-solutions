import java.io.*;

public class INVITIATION_DEVASTATION {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String a = in.readLine ();
    int T = Integer.parseInt (in.readLine ());
    String [] names = new String [T];
    
    for (int i = 0; i < T; i++) {
      names [i] = in.readLine ();
    }
    
    for (String i : names) {
      System.out.println (a.replaceAll ("[>]", i));
    }
  }
}