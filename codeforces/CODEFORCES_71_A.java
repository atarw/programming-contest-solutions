import java.io.*;

public class CODEFORCES_71_A {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    PrintWriter out = new PrintWriter (System.out);
    
    int N = Integer.parseInt (in.readLine ());
    String ln;
    
    while (N --> 0) {
      ln = in.readLine ();
      
      if (ln.length () > 10) {
        out.print (ln.charAt (0)); out.print (ln.length () - 2); out.println (ln.charAt (ln.length () - 1));
      }
      else {
        out.println (ln);
      }
    }
    
    out.close ();
  }
}