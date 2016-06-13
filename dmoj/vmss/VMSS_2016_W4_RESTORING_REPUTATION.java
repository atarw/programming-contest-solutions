import java.io.*;

public class VMSS_2016_W4_RESTORING_REPUTATION {
  
  static int D, I, R;
  static int [][] cache;
  
  public static int min (String S, String F) {
    
    if (cache [S.length () - 1][F.length () - 1] == 0) {
      for (int x = 0; x < S.length (); x++) {
        for (int y = 0; y < F.length (); y++) {
          if (F.charAt (y) != S.charAt (x)) {
            cache [x + 1][y + 1] = Math.min (Math.min (I + min (new StringBuilder (S).insert (x, F.charAt (y)).toString (), F), D + min (S.substring (x), F)), R + min (new StringBuilder (S).replace (x, x, F.charAt (y) + "").toString (), F));
          }
        }
      }
    } 
    
    return cache [S.length ()][F.length ()];
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    
    D = Integer.parseInt (t [0]);
    I = Integer.parseInt (t [1]);
    R = Integer.parseInt (t [2]);
    
    t = in.readLine ().split (" ");
    
    cache = new int [t [0].length () + 1][t [1].length () + 1];
    
    System.out.println (min (t [0], t[1]));
  }
}