import java.io.*;
import java.util.regex.*;

public class H_OR_S {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String s = in.readLine ();
    
    int sCount = 0, hCount = 0;
    Pattern sf = Pattern.compile (":-\\(");
    Pattern hf = Pattern.compile (":-\\)");
    Matcher matcher = sf.matcher (s);
    
    while (matcher.find ()) {
      sCount++;
    }
    
    matcher = hf.matcher (s);
    
    while (matcher.find ()) {
      hCount++;
    }
    
    if (hCount == sCount) {
      if (hCount == 0) {
        System.out.println ("none");
      }
      else {
        System.out.println ("unsure");
      }
    }
    else if (sCount > hCount) {
      System.out.println ("sad");
    }
    else {
      System.out.println ("happy");
    }
  }
}