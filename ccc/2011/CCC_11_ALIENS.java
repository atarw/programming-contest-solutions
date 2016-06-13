import java.io.*;

public class CCC_11_ALIENS {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int A = Integer.parseInt (in.readLine ());
    int E = Integer.parseInt (in.readLine ());
    
    if (A >= 3 && E <= 4) {
      System.out.println ("TroyMartian");
    }
    if (A <= 6 && E >= 2) {
      System.out.println ("VladSaturnian");
    }
    if (A <= 2 && E <= 3) {
      System.out.println ("GraemeMercurian");
    }
  }
}