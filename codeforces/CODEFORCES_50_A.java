import java.io.*;

public class CODEFORCES_50_A {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in)); PrintWriter out = new PrintWriter (System.out);
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    
    out.print ((N * M) / 2);
    out.close ();
  }
}