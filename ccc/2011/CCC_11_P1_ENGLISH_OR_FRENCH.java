import java.io.*;

public class CCC_11_P1_ENGLISH_OR_FRENCH {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    StringBuilder s = new StringBuilder ();
    
    for (int n = 0; n < N; n++) {
      s.append (in.readLine ());
    }
    
    int tCount = 0, sCount = 0;
    
    for (int i = 0; i < s.length (); i++) {
      if (s.charAt (i) == 's' || s.charAt (i) == 'S') {
        sCount++;
      }
      else if (s.charAt (i) == 't' || s.charAt (i) == 'T') {
        tCount++;
      }
    }
    
    if (tCount <= sCount) {
      System.out.println ("French");
    }
    else {
      System.out.println ("English");
    }
  }
}