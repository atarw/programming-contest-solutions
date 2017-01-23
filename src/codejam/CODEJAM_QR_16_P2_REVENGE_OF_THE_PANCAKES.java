import java.io.*;

public class CODEJAM_QR_16_P2_REVENGE_OF_THE_PANCAKES {
  
  public static char opp (char c) {
    return c == '-' ? '+' : '-';
  }
  
  public static int ans (String t) {
    
    t = t.replaceAll ("(\\+)\\1+", "+");
    t = t.replaceAll ("(\\-)\\1+", "-");
    
    //System.out.println (t);
    
    StringBuilder ln = new StringBuilder (t);
    int lastBlank = ln.length () - 1;
    int time = 0;
    char tmp = ' ';
    
    while (true) {
      //System.out.println (ln + " " + lastBlank);
      
      for (int i = lastBlank; i >= 0; i--) {
        lastBlank = i;
        
        if (ln.charAt (i) == '-') {
          break;
        }
      }
      
      if (lastBlank == 0 && ln.charAt (lastBlank) == '+') {
        break;
      }
      else {
        if (ln.charAt (0) != ln.charAt (lastBlank)) {
          ln.setCharAt (0, opp (ln.charAt (0)));
        }
        else {
          for (int i = 0; i <= lastBlank / 2; i++) {
            tmp = ln.charAt (i);
            ln.setCharAt (i, opp (ln.charAt (lastBlank - i)));
            ln.setCharAt (lastBlank - i, opp (tmp));
          }
        }
        
        time++;
      }
      
      //System.out.println (ln);
    }
    
    return time;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ());
    
    for (int t = 1; t <= T; t++) {
      System.out.println ("Case #" + t + ": " + ans (in.readLine ()));
    }
  }
}