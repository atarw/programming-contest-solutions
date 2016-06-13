import java.io.*;

public class CCC_05_P5_BANANAS {
  
  public static boolean a (String ln) {
    //System.out.println ("A: " + ln);
    
    if (ln.equals ("A")) {
      return true;
    }
    
    if (ln.length () >= 3 && ln.charAt (0) == 'B' & ln.charAt (ln.length () - 1) == 'S') {
      return monkey (ln.substring (1, ln.length () - 1));
    }
    
    return false;
  }
  
  public static boolean monkey (String ln) {
    //System.out.println ("M: " + ln);
    
    if (a (ln)) {
      return true;
    }
    
    for (int i = 0; i < ln.length (); i++) {
      if (ln.charAt (i) == 'N' && a (ln.substring (0, i)) && monkey (ln.substring (i + 1))) {
        return true;
      }
    }
    
    return false;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String ln;
    
    while (!(ln = in.readLine ()).equals ("X")) {
      System.out.println (monkey (ln) ? "YES" : "NO");
    }
  }
}