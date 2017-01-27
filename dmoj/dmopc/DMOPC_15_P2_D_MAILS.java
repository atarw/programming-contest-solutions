import java.io.*;

public class DMOPC_15_P2_D_MAILS {
  
  static String ln;
  static String [] arr = {"elpsycongroo", "tuturu", "superhacker", "myfork"};
  static String [] name = {"Okabe", "Mayuri", "Daru", "Kurisu"};
  
  public static boolean sub (int n, int m, String N, String M) {//n = ln char, m = arr char    
    if (n == N.length ()) {
      return true;
    }
    
    if (m == M.length ()) {
      return false;
    }
    
    if (N.charAt (n) == M.charAt (m)) {
      return sub (n + 1, m + 1, N, M);
    }
    
    return sub (n, m + 1, N, M);
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    boolean notOne = false;
    
    for (int n = 0; n < N; n++) {
      ln = in.readLine ();
      notOne = false;
      
      for (int i = 0; i < 4; i++) {
        if (sub (0, 0, Math.min (ln.length (), arr [i].length ()) == ln.length () ? ln : arr [i], Math.min (ln.length (), arr [i].length ()) == ln.length () ? arr [i] : ln)) {
          System.out.print ((!notOne ? "" : " or ") + name [i]);
          notOne = true;
        }
      }
      
      if (!notOne) {
        System.out.print ("SERN spy!");
      }
      
      System.out.println ();
    }
  }
}